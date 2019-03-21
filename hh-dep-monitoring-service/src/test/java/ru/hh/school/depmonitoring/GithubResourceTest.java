package ru.hh.school.depmonitoring;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.testbase.NabTestBase;
import ru.hh.school.depmonitoring.config.TestConfig;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = TestConfig.class)
public class GithubResourceTest extends NabTestBase {
    @Test
    public void resource() {
        Response response = target("/admin/github/sync")
                .request()
                .post(null);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Override
    protected NabApplication getApplication() {
        return NabApplication.builder().configureJersey().bindToRoot().build();
    }
}
