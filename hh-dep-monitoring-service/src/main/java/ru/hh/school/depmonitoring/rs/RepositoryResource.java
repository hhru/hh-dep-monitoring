package ru.hh.school.depmonitoring.rs;


import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.service.RepositoryService;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Named
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/repository")
public class RepositoryResource {
    private RepositoryService service;

    public RepositoryResource(RepositoryService service) {
        this.service = service;
    }

    @GET
    @Path("/page")
    public PageDto<RepositoryDto> getRepositriesPage(@DefaultValue("0") @QueryParam("page") int page,
                                                     @DefaultValue("10") @QueryParam("perPage") int perPage) {
        PageRequestDto requestDto = PageRequestDto.builder()
                .withPage(page)
                .withPerPage(perPage)
                .build();
        return service.getRepositoryPage(requestDto);
    }

    @GET
    @Path("/{repositoryId}")
    public Response getRepositoryById(@PathParam("repositoryId") long id) {
        Optional<RepositoryDto> optionalDto = service.getOneItem(id);
        if (optionalDto.isPresent()) {
            return Response.ok(optionalDto.get()).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{repositoryId}")
    public Response updateRepositoryById(@Nonnull RepositoryDto dto, @PathParam("repositoryId") long id) {
        if (id == dto.getRepositoryId()) {
            return Response.status(Response.Status.OK).entity(service.update(dto)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
