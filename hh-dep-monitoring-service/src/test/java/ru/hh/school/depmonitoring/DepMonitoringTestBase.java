package ru.hh.school.depmonitoring;

import org.junit.After;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.depmonitoring.config.TestConfig;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.rs.BambooTestResource;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.ObjectMapperContextResolver;

import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;

@ContextConfiguration(classes = TestConfig.class)
public class DepMonitoringTestBase extends NabTestBase {

    @Inject
    private DBUtils dbUtils;

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder()
                .configureJersey()
                .registerResources(ObjectMapperContextResolver.class, BambooTestResource.class)
                .bindToRoot()
                .build();
    }

    @After
    public void clean() {
        dbUtils.cleanTable(RepositoryLink.class);
        dbUtils.cleanTable(Relation.class);
        dbUtils.cleanTable(Repository.class);
        dbUtils.cleanTable(Artifact.class);
        dbUtils.cleanTable(ArtifactVersion.class);
        dbUtils.cleanTable(Dependency.class);
    }

    @Override
    protected ClientBuilder getClientBuilder() {
        return super.getClientBuilder()
                .register(ObjectMapperContextResolver.class);
    }
}
