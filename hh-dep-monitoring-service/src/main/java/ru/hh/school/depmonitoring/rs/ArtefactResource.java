package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.ArtefactTreeDto;
import ru.hh.school.depmonitoring.service.ArtefactService;
import ru.hh.school.depmonitoring.utils.TtlLoader;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.function.Supplier;

@Named
@Singleton
@Path("/artefact")
@Produces(MediaType.APPLICATION_JSON)
public class ArtefactResource {
    private final ArtefactService artefactService;
    private final TtlLoader<List<ArtefactTreeDto>> treeTtlLoader;

    public ArtefactResource(ArtefactService artefactService) {
        this.artefactService = artefactService;
        this.treeTtlLoader = new TtlLoader<>(300000, new Supplier<List<ArtefactTreeDto>>() {
            @Override
            public List<ArtefactTreeDto> get() {
                return artefactService.getFullTree();
            }
        });
    }

    @GET
    @Path("/full-tree")
    public List<ArtefactTreeDto> getTree() {
        return treeTtlLoader.get();
    }

    @GET
    @Path("/names")
    public List<String> getNames() {
        return artefactService.getNames();
    }

    @GET
    @Path("/suggest/{keyword}")
    public List<String> getNamesContains(@PathParam("keyword") String keyword) {
        return artefactService.getNamesContains(keyword);
    }

}
