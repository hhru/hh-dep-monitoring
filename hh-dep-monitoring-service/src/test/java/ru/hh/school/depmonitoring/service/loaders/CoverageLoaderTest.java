package ru.hh.school.depmonitoring.service.loaders;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.entities.RepositoryLinkType;
import ru.hh.school.depmonitoring.exceptions.LoadException;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class CoverageLoaderTest extends DepMonitoringTestBase {

    @Inject
    public CoverageLoader coverageLoader;

    @Inject
    public DBUtils dbUtils;

    @Test()
    public void saveCoverageFromSonarCloudTest() throws LoadException, IOException {
        Repository repository = StructCreator.createRepositoryEntity(1, LocalDateTime.now());
        dbUtils.addItemToRepositoryTable(repository);
        RepositoryLink repositoryLink = StructCreator.createRepositoryLinkEntity(1,
                "http://sonarcloud.io/dashboard?id=key",
                RepositoryLinkType.SONARCLOUD);
        dbUtils.addItemToRepositoryLinkTable(repositoryLink);
        assertTrue(dbUtils.getCoveragesCount() == 0);
        coverageLoader.loadCoverage();
        assertTrue(dbUtils.getCoveragesCount() == 1);
        coverageLoader.loadCoverage();
        assertTrue(dbUtils.getCoveragesCount() == 1);
    }

}
