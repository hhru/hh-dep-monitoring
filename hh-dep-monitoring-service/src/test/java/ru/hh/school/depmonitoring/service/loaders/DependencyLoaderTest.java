package ru.hh.school.depmonitoring.service.loaders;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.ArtifactDao;
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

    @Test
    public void loadTestWithoutRepo() {
        dependencyLoader.saveDependencyData();
        var artifactList = dbUtils.doInTransaction(artifactDao::findAll);
        assertEquals(0, artifactList.size());
    }

    @Test
    public void loadTestWithRepo() {
        var repositoryDto = StructCreator.createRepositoryDto(1L, "nuts-and-bolts");
        dbUtils.addItemToRepositoryTable(repositoryDto);
        dependencyLoader.saveDependencyData();
        var artifactList = dbUtils.doInTransaction(
                () -> artifactDao
                .findAll()
                .stream()
                .filter((artifact) -> artifact.getRepository() != null && Objects.equals(artifact.getRepository().getName(), "nuts-and-bolts"))
                .collect(Collectors.toList())
        );
        assertEquals(9, artifactList.size());
        var repository = artifactList.get(0).getRepository();
        assertEquals(repositoryDto.getRepositoryId(), repository.getRepositoryId());
        assertEquals(repositoryDto.getName(), repository.getName());
    }
}
