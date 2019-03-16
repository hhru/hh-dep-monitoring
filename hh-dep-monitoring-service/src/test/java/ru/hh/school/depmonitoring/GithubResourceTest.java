package ru.hh.school.depmonitoring;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class GithubResourceTest extends DepMonitoringTestBase {
    @Test
    public void resource() {
        Response response = target("/admin/github/sync")
                .request()
                .post(null);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
