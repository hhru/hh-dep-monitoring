package ru.hh.school.depmonitoring.service.loaders;

import org.junit.Test;
import ru.hh.nab.testbase.JettyTestContainer;
import ru.hh.nab.testbase.spring.NabTestContext;
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
import ru.hh.school.depmonitoring.exceptions.LoadRuntimeException;
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

    @Inject
    private NabTestContext testContext;

    @Test
    public void loadTestWithoutRepo() {
        dependencyLoader.saveDependencyData();
        var artifactList = dbUtils.doInTransaction(artifactDao::findAll);
        assertEquals(0, artifactList.size());
    }

    @Test
    public void loadTestWithRepo() {
        createRepository(2L, "jclient-common");
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

    @Test(expected = LoadRuntimeException.class)
    public void loadTestWithBrokenLink() {
        dependencyLoader.saveDependencyData(
                JettyTestContainer.getServerAddress(testContext.getPortHolder().getPort()).toString().concat("/relations/types")
        );
    }

    private Repository createRepository() {
        return createRepository(1L, "nuts-and-bolts");
    }

    private Repository createRepository(long repositoryId, String repositoryName) {
        return dbUtils.addItemToRepositoryTable(StructCreator.createRepositoryDto(repositoryId, repositoryName));
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
        artifactDao.changeExpression(null, null, repository.getName());
        assertEquals(repositoryOriginal.getRepositoryId(), repository.getRepositoryId());
        assertEquals(repositoryOriginal.getName(), repository.getName());
    }
}
