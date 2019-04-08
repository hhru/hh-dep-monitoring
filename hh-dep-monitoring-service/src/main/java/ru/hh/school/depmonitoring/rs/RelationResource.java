package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.service.RelationService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
@Singleton
@Path("/relations/")
public class RelationResource {
    private final RelationService relationService;

    public RelationResource(RelationService relationService) {
        this.relationService = relationService;
    }

    @GET
    @Path("/dependOn/{repositoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelationDto> getRelationsDependOn(@PathParam("repositoryId") long repositoryId) {
        return relationService.getRelationsDependOn(repositoryId);
    }

    @GET
    @Path("/dependOut/{repositoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RelationDto> getRelationsDependOut(@PathParam("repositoryId") long repositoryId) {
        return relationService.getRelationsDependOut(repositoryId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertRelation(RelationDto relationDto) {
        relationService.insertRelation(relationDto);
    }

    @PUT
    @Path("/{relationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRelation(@PathParam("relationId") int relationId, RelationDto relationDto) {
        if (relationDto != null && relationService.updateRelation(relationId, relationDto)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{relationId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteRelation(@PathParam("relationId") int relationId) {
        relationService.deleteRelation(relationId);
    }
}
