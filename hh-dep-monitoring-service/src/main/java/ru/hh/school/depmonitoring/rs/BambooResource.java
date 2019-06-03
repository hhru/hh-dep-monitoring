package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.service.SyncService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

@Named
@Singleton
@Path("/admin/bamboo/")
public class BambooResource {
    private final SyncService syncService;

    public BambooResource(SyncService syncService) {
        this.syncService = syncService;
    }

    @POST
    @Path("/sync")
    public void syncBamboo() {
        syncService.syncBamboo();
    }

    @POST
    @Path("/sync/with-link")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void syncBambooWithOwnLink(Form linkForm) {
        String bambooLink = linkForm.asMap().get("bambooLink").get(0);
        syncService.syncBamboo(bambooLink);
    }
}
