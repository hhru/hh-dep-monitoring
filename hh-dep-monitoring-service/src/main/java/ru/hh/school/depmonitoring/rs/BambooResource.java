package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.service.SyncService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
}
