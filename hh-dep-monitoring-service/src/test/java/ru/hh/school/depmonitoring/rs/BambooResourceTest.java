package ru.hh.school.depmonitoring.rs;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class BambooResourceTest extends DepMonitoringTestBase {
    @Inject
    private String bambooLink;

    @Test
    public void syncBamboo() {
        Response response = target("/admin/bamboo/sync")
                .request()
                .post(null);
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void syncBambooWithOwnLink() {
        var form = new Form();
        form.param("bambooLink", bambooLink);
        Response response = target("/admin/bamboo/sync/with-link")
                .request()
                .post(Entity.form(form));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
}
