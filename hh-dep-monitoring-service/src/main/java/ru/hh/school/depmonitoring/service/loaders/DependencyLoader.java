package ru.hh.school.depmonitoring.service.loaders;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.ArtifactDao;
import ru.hh.school.depmonitoring.dao.ArtifactVersionDao;
import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.bamboo.BambooArtifactDto;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import java.util.Arrays;
import java.util.List;
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

    private static final Logger log = LoggerFactory.getLogger(DependencyLoader.class);

    public DependencyLoader(
            String bambooLink,
            RepositoryDao repositoryDao,
            ArtifactDao artifactDao,
            ArtifactVersionDao artifactVersionDao,
            DependencyDao dependencyDao
    ) {
        this.bambooLink = bambooLink;
        this.repositoryDao = repositoryDao;
        this.artifactDao = artifactDao;
        this.artifactVersionDao = artifactVersionDao;
        this.dependencyDao = dependencyDao;
    }

    private List<BambooArtifactDto> getDataFromBamboo() {
        return ClientBuilder.newBuilder()
                .build()
                .target(bambooLink)
                .request()
                .get()
                .readEntity(new GenericType<List<BambooArtifactDto>>() {
                });
    }

    @Transactional
    public void saveDependencyData() {
        saveDependencyData(getDataFromBamboo(), null);
    }

    private void saveDependencyData(List<BambooArtifactDto> artifactDtoList, Repository parentRepository) {
        for (var bambooArtifactDto: artifactDtoList) {
            String repoName = bambooArtifactDto.getRepoName();
            Optional<Repository> repositoryOptional = (repoName == null || repoName.isEmpty()) ?
                    Optional.empty() :
                    repositoryDao.findRepositoryByName(bambooArtifactDto.getRepoName());
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

                saveArtifact(groupName, artifactName, version, repositoryOfArtifact, parentRepository);

                saveDependencyData(bambooArtifactDto.getDependencyTree(), parentRepository == null ? repositoryOfArtifact : parentRepository);
            }
        }
    }

    private List<Integer> getVersionList(String version) {
        return Arrays.stream(version.split("\\D"))
                .filter(StringUtils::isNumeric)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void saveArtifact(String groupName, String artifactName, String version, Repository repository, Repository parentRepository) {
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

        if (parentRepository != null) {
            var dependencyOptional = dependencyDao.findByRepositoryAndArtifact(parentRepository, artifact);
            boolean needCreate = true;
            if (dependencyOptional.isPresent()) {
                if (artifactVersionOptional.isPresent() && artifactVersionOptional.get().equals(dependencyOptional.get().getArtifactVersion())) {
                    needCreate = false;
                } else {
                    dependencyDao.delete(dependencyOptional.get());
                }
            }
            if (needCreate) {
                Dependency dependency = new Dependency(parentRepository, artifactVersion);
                dependencyDao.create(dependency);
            }
        }
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
}
