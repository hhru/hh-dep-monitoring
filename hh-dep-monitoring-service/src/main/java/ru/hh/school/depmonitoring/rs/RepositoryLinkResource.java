package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.service.RepositoryLinkService;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Named
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/repository-link")
public class RepositoryLinkResource {
    private final RepositoryLinkService repositoryLinkService;

    public RepositoryLinkResource(RepositoryLinkService repositoryLinkService) {
        this.repositoryLinkService = repositoryLinkService;
    }

    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") int id) {
        Optional<RepositoryLinkDto> repositoryLinkDto = repositoryLinkService
                .findRepositoryLinkById(id);
        if (repositoryLinkDto.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(repositoryLinkDto.get()).build();
    }

    @GET
    @Path("/for-repository/{repositoryId}")
    public List<RepositoryLinkDto> findForRepository(@PathParam("repositoryId") long repositoryId) {
        return repositoryLinkService.findForRepository(repositoryId);
    }

    @PUT
    @Path("/{id}")
    public Response updateById(@Nonnull RepositoryLinkDto repositoryLinkDto, @PathParam("id") int id) {
        if (id != repositoryLinkDto.getRepositoryLinkId()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(repositoryLinkService.update(repositoryLinkDto))
                .build();
    }

    @POST
    @Path("/")
    public RepositoryLinkDto saveRepositoryLink(RepositoryLinkDto repositoryLinkDto) {
        return repositoryLinkService.saveRepositoryLink(repositoryLinkDto);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") int id) {
        repositoryLinkService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
