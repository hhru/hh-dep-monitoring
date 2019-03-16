package ru.hh.school.depmonitoring;

import org.junit.After;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.config.TestConfig;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.utils.ObjectMapperContextResolver;

import javax.inject.Inject;

@ContextConfiguration(classes = TestConfig.class)
public class DepMonitoringTestBase extends NabTestBase {

    @Inject
    private DBUtils dbUtils;

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder()
                .configureJersey()
                .registerResources(ObjectMapperContextResolver.class)
                .bindToRoot()
                .build();
    }

    @After
    public void clean() {
        dbUtils.cleanTable(Repository.class);
        dbUtils.cleanTable(Relation.class);
    }
}
