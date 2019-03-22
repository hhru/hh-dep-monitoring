package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.service.SyncService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Named
@Singleton
@Path("/admin/github/")
public class GithubResource {
    private final SyncService syncService;

    public GithubResource(SyncService syncService) {
        this.syncService = syncService;
    }

    @POST
    @Path("/sync")
    public void syncGH() {
        syncService.syncGithub();
    }
}
