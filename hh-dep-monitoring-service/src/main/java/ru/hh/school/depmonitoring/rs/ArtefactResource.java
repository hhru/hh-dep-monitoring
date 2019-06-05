package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.ArtefactTreeDto;
import ru.hh.school.depmonitoring.service.ArtefactService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Singleton
@Path("/artefact")
@Produces(MediaType.APPLICATION_JSON)
public class ArtefactResource {
    private final ArtefactService artefactService;

    public ArtefactResource(ArtefactService artefactService) {
        this.artefactService = artefactService;
    }

    @GET
    @Path("/full-tree")
    public List<ArtefactTreeDto> getTree() {
        return artefactService.getFullTree();
    }
}
