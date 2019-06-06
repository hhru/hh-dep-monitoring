package ru.hh.school.depmonitoring.service.loaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.depmonitoring.dao.ArtifactDao;
import ru.hh.school.depmonitoring.dao.ArtifactVersionDao;
import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.bamboo.BambooArtifactDto;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.Event;
import ru.hh.school.depmonitoring.entities.EventType;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.exceptions.LoadExceptionType;
import ru.hh.school.depmonitoring.exceptions.LoadRuntimeException;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.utils.TransactionUtils;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@Singleton
public class DependencyLoader {
    private final String bambooLink;
    private final RepositoryDao repositoryDao;
    private final ArtifactDao artifactDao;
    private final ArtifactVersionDao artifactVersionDao;
    private final DependencyDao dependencyDao;
    private final EventDao eventDao;
    private final RelationDao relationDao;
    private final TransactionUtils transactionUtils;

    private static final Logger log = LoggerFactory.getLogger(DependencyLoader.class);

    public DependencyLoader(
            String bambooLink,
            RepositoryDao repositoryDao,
            ArtifactDao artifactDao,
            ArtifactVersionDao artifactVersionDao,
            DependencyDao dependencyDao,
            EventDao eventDao,
            RelationDao relationDao,
            TransactionUtils transactionUtils) {
        this.bambooLink = bambooLink;
        this.repositoryDao = repositoryDao;
        this.artifactDao = artifactDao;
        this.artifactVersionDao = artifactVersionDao;
        this.dependencyDao = dependencyDao;
        this.eventDao = eventDao;
        this.relationDao = relationDao;
        this.transactionUtils = transactionUtils;
    }

    private List<BambooArtifactDto> getDataFromBamboo(String bambooLink) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] allBytes = ClientBuilder.newBuilder()
                .build()
                .target(bambooLink)
                .request()
                .get()
                .readEntity(InputStream.class)
                .readAllBytes();
        return List.of(mapper.readValue(allBytes, BambooArtifactDto[].class));
    }

    public void saveDependencyData() {
        saveDependencyData(bambooLink);
    }

    public void saveDependencyData(String bambooLink) {
        try {
            saveDependencyData(getDataFromBamboo(bambooLink), null, null);
            transactionUtils.doInTransaction((Runnable) this::updateDependencies);
        } catch (IOException e) {
            throw new LoadRuntimeException("Error on getDataFromBamboo", e, LoadExceptionType.CONNECT);
        }
    }

    private void saveDependencyData(List<BambooArtifactDto> artifactDtoList, Repository parentRepository, Dependency parentDependency) {
        for (var bambooArtifactDto: artifactDtoList) {
            String repoName = bambooArtifactDto.getRepoName();
            Optional<Repository> repositoryOptional = (repoName == null || repoName.isEmpty()) ?
                    Optional.empty() :
                    transactionUtils.doInTransaction(() -> repositoryDao.findRepositoryByName(bambooArtifactDto.getRepoName()));
            if (repositoryOptional.isEmpty() && parentRepository == null) {
                log.error("Can't find repository with name {} in saveBambooDate", bambooArtifactDto.getRepoName());
                continue;
            }
            Repository repositoryOfArtifact = repositoryOptional.orElse(null);

            String[] keyList = bambooArtifactDto.getKey().split(":");
            if (keyList.length != 3) {
                log.error("Can't parse bamboo key to artifact name, group name and version ({})", bambooArtifactDto.getKey());
            } else {
                String groupName = keyList[0];
                String artifactName = keyList[1];
                String version = keyList[2];
                var repository = (parentRepository == null ? repositoryOfArtifact : parentRepository);
                var dependency = transactionUtils.doInTransaction(
                        () -> saveArtifact(groupName, artifactName, version, repositoryOfArtifact, repository, parentDependency)
                );
                saveDependencyData(bambooArtifactDto.getDependencyTree(), repository, dependency);
            }
        }
    }

    private List<Integer> getVersionList(String version) {
        return Arrays.stream(version.split("\\D"))
                .filter(StringUtils::isNumeric)
                .filter(s -> s.length() < 5)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private Dependency saveArtifact(
            String groupName,
            String artifactName,
            String version,
            Repository repository,
            Repository parentRepository,
            Dependency parentDependency
    ) {
        var artifactOptional = artifactDao.findByName(groupName, artifactName);
        var artifact = artifactOptional.orElseGet(() -> createArtifact(artifactName, groupName, repository));
        if (repository != null &&
                (artifact.getRepository() == null || !(repository.getRepositoryId().equals(artifact.getRepository().getRepositoryId())))) {
            artifact.setRepository(repository);
            artifactDao.update(artifact);
        }

        Optional<ArtifactVersion> artifactVersionOptional = artifactOptional.isPresent() ?
                artifactVersionDao.findByArtifactAndVersion(artifact, version) :
                Optional.empty();
        var artifactVersion = artifactVersionOptional.orElseGet(() -> createArtifactVersion(artifact, version));

        Optional<Dependency> dependencyOptional = getDependency(parentRepository, artifact, parentDependency);
        if (dependencyOptional.isPresent()) {
            Dependency dependency = dependencyOptional.get();
            if (artifactVersionOptional.isPresent() && artifactVersionOptional.get().equals(dependency.getArtifactVersion())) {
                return dependency;
            }
            createEvent(parentRepository.getRepositoryId(), dependency.getArtifactVersion(), artifactVersion);
            deleteDependency(dependency);
        }
        Dependency dependency = new Dependency(parentRepository, artifactVersion, parentDependency);
        dependencyDao.create(dependency);
        return dependency;
    }

    private Optional<Dependency> getDependency(Repository parentRepository, Artifact artifact, Dependency parentDependency) {
        var dependencyList = dependencyDao.findByRepositoryAndArtifact(parentRepository, artifact);
        for (var dependencyItem : dependencyList) {
            if (parentDependency == null && dependencyItem.getParentDependency() == null ||
                    parentDependency != null && getHeadDependency(parentDependency).equals(getHeadDependency(dependencyItem))) {
                return Optional.of(dependencyItem);
            }
        }
        return Optional.empty();
    }

    private Dependency getHeadDependency(Dependency dependency) {
        Dependency headDependency = dependency;
        while (headDependency.getParentDependency() != null) {
            headDependency = headDependency.getParentDependency();
        }
        return headDependency;
    }

    private void deleteDependency(Dependency dependency) {
        var dependencyList = dependencyDao.findByParentDependency(dependency);
        for (var dependencyItem : dependencyList) {
            deleteDependency(dependencyItem);
        }
        dependencyDao.delete(dependency);
    }

    private Artifact createArtifact(String artifactName, String groupName, Repository repository) {
        Artifact artifact = new Artifact(artifactName, groupName, repository);
        artifactDao.create(artifact);
        return artifact;
    }

    private ArtifactVersion createArtifactVersion(Artifact artifact, String version) {
        List<Integer> versionList = getVersionList(version);
        Integer[] versionArray = versionList.toArray(new Integer[Math.max(3, versionList.size())]);
        Integer versionMajor = versionArray[0];
        Integer versionMinor = versionArray[1];
        Integer versionMicro = versionArray[2];
        ArtifactVersion artifactVersion = new ArtifactVersion(artifact, versionMajor, versionMinor, versionMicro, version);
        artifactVersionDao.create(artifactVersion);
        return artifactVersion;
    }

    private void createEvent(Long repositoryId, ArtifactVersion oldArtifactVersion, ArtifactVersion newArtifactVersion) {
        var event = new Event();
        event.setCreatedAt(LocalDateTime.now());
        event.setRepositoryId(repositoryId);
        event.setArtifactId(oldArtifactVersion.getArtifact().getArtifactId());
        if (!Objects.equals(oldArtifactVersion.getVersionMajor(), newArtifactVersion.getVersionMajor())) {
            event.setType(EventType.VERSION_MAJOR_CHANGE);
        } else if (!Objects.equals(oldArtifactVersion.getVersionMinor(), newArtifactVersion.getVersionMinor())) {
            event.setType(EventType.VERSION_MINOR_CHANGE);
        } else if (!Objects.equals(oldArtifactVersion.getVersionMicro(), newArtifactVersion.getVersionMicro())) {
            event.setType(EventType.VERSION_MICRO_CHANGE);
        } else {
            event.setType(EventType.VERSION_CHANGE);
        }
        event.setDescription("Version changed from " + oldArtifactVersion.getVersion() + " to " + newArtifactVersion.getVersion());
        eventDao.create(event);
    }

    private void updateDependencies() {
        artifactDao.findAll()
                .stream()
                .filter(artifact -> artifact.getRepository() != null)
                .forEach(artifact -> updateDependencies(artifact, artifact.getRepository()));
    }

    private void updateDependencies(Artifact childArtifact, Repository childRepository) {
        var artifactList = dependencyDao.findByArtifact(childArtifact)
                    .stream()
                    .map(Dependency::getParentDependency)
                    .filter(Objects::nonNull)
                    .map(dependency -> dependency.getArtifactVersion().getArtifact())
                    .collect(Collectors.toList());
        for (var artifact : artifactList) {
            var repository = artifact.getRepository();
            if (repository != null) {
                checkAndCreateRelation(childRepository, repository);
                updateDependencies(artifact, repository);
            } else {
                updateDependencies(artifact, childRepository);
            }
        }
    }

    private void checkAndCreateRelation(Repository childRepository, Repository parentRepository) {
        if (!Objects.equals(childRepository, parentRepository)) {
            var relationOptional = relationDao.findRelationByMainAndDependentRepository(parentRepository, childRepository);
            if (relationOptional.isEmpty()) {
                createRelation(childRepository, parentRepository);
            }
        }
    }

    private void createRelation(Repository childRepository, Repository parentRepository) {
        Relation relation = new Relation();
        relation.setRepositoryFrom(parentRepository);
        relation.setRepositoryTo(childRepository);
        relation.setPriority(RepositoryRelationPriority.OPTIONAL);
        relationDao.create(relation);
    }
}
