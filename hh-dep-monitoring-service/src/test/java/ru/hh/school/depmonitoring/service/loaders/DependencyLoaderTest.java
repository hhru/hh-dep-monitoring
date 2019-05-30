package ru.hh.school.depmonitoring.service.loaders;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.ArtifactDao;
import ru.hh.school.depmonitoring.dao.ArtifactVersionDao;
import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.EventType;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DependencyLoaderTest extends DepMonitoringTestBase {
    @Inject
    private DependencyLoader dependencyLoader;

    @Inject
    private DBUtils dbUtils;

    @Inject
    private ArtifactDao artifactDao;

    @Inject
    private ArtifactVersionDao artifactVersionDao;

    @Inject
    private DependencyDao dependencyDao;

    @Inject
    private EventDao eventDao;

    @Test
    public void loadTestWithoutRepo() {
        dependencyLoader.saveDependencyData();
        var artifactList = dbUtils.doInTransaction(artifactDao::findAll);
        assertEquals(0, artifactList.size());
    }

    @Test
    public void loadTestWithRepo() {
        updateDependencyData(createRepository());
    }

    @Test
    public void loadTestWithChangeVersion() {
        var repository = createRepository();
        var artifact = new Artifact("nab-logging", "ru.hh.nab", repository);
        dbUtils.doInTransaction(() -> artifactDao.create(artifact));
        var artifactVersion = new ArtifactVersion(artifact, 4, 18, 5, "4.18.5");
        dbUtils.doInTransaction(() -> artifactVersionDao.create(artifactVersion));
        var dependency = new Dependency(repository, artifactVersion, null);
        dbUtils.doInTransaction(() -> dependencyDao.create(dependency));
        updateDependencyData(repository);
        dbUtils.doInTransaction(() -> {
            var dependencyList = dependencyDao
                    .findByRepositoryAndArtifact(repository, artifact)
                    .stream()
                    .filter((dep -> dep.getParentDependency() == null))
                    .collect(Collectors.toList());
            assertEquals(1, dependencyList.size());
            var dependencyFromDao = dependencyList.get(0);
            assertEquals(repository.getRepositoryId(), dependencyFromDao.getRepository().getRepositoryId());
            assertEquals("4.19.6", dependencyFromDao.getArtifactVersion().getVersion());
        });
        var eventList = dbUtils.doInTransaction(eventDao::findAll);
        assertEquals(1, eventList.size());
        var event = eventList.get(0);
        assertEquals(repository.getRepositoryId(), event.getRepositoryId());
        assertEquals(artifact.getArtifactId(), event.getArtifactId());
        assertEquals(EventType.VERSION_MINOR_CHANGE, event.getType());
    }

    private Repository createRepository() {
        return dbUtils.addItemToRepositoryTable(StructCreator.createRepositoryDto(1L, "nuts-and-bolts"));
    }

    private void updateDependencyData(Repository repositoryOriginal) {
        dependencyLoader.saveDependencyData();
        var artifactList = dbUtils.doInTransaction(
                () -> artifactDao
                        .findAll()
                        .stream()
                        .filter((artifact) -> artifact.getRepository() != null &&
                                Objects.equals(artifact.getRepository().getName(), "nuts-and-bolts"))
                        .collect(Collectors.toList())
        );
        assertEquals(9, artifactList.size());
        var repository = artifactList.get(0).getRepository();
        assertEquals(repositoryOriginal.getRepositoryId(), repository.getRepositoryId());
        assertEquals(repositoryOriginal.getName(), repository.getName());
    }
}
