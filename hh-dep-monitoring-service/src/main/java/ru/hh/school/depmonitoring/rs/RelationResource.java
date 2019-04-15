package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
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
import java.util.Map;

@Named
@Singleton
@Path("/relations/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RelationResource {
    private final RelationService relationService;

    public RelationResource(RelationService relationService) {
        this.relationService = relationService;
    }

    @GET
    @Path("/types")
    public RepositoryRelationPriority[] getValidRepositoryTypes() {
        return RepositoryRelationPriority.values();
    }

    @GET
    @Path("/depend-on/{repositoryId}")
    public Map<RepositoryRelationPriority, List<RelationDto>> getRelationsDependOn(@PathParam("repositoryId") long repositoryId) {
        return relationService.getRelationsDependOn(repositoryId);
    }

    @GET
    @Path("/depend-out/{repositoryId}")
    public Map<RepositoryRelationPriority, List<RelationDto>> getRelationsDependOut(@PathParam("repositoryId") long repositoryId) {
        return relationService.getRelationsDependOut(repositoryId);
    }

    @POST
    public RelationDto insertRelation(RelationDto relationDto) {
        return relationService.insertRelation(relationDto);
    }

    @PUT
    @Path("/{relationId}")
    public Response updateRelation(@PathParam("relationId") int relationId, RelationDto relationDto) {
        if (relationDto != null) {
            return Response.ok(relationService.updateRelation(relationId, relationDto)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{relationId}")
    public void deleteRelation(@PathParam("relationId") int relationId) {
        relationService.deleteRelation(relationId);
    }
}
