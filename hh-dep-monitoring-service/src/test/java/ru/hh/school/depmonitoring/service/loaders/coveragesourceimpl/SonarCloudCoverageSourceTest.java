package ru.hh.school.depmonitoring.service.loaders.coveragesourceimpl;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.exceptions.LoadException;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SonarCloudCoverageSourceTest extends DepMonitoringTestBase {

    @Inject
    public SonarCloudCoverageSource sonarCloudCoverageSource;

    @Test()
    public void coverageLoadTest() throws LoadException, IOException {
        float covereage = sonarCloudCoverageSource.getCoverage("key");
        assertEquals(10.0f, covereage, 0.01f);
    }

}
