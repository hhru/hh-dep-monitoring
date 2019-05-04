package ru.hh.school.depmonitoring.rs;


import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.RepositoryType;
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
import java.util.List;
import java.util.Optional;

@Named
@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/repository")
public class RepositoryResource {
    private RepositoryService repositoryService;

    public RepositoryResource(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RepositoryDto> getAllRepositories() {
        return repositoryService.getFullList();
    }


    @GET
    @Path("/page")
    public PageDto<RepositoryDto> getRepositriesPage(@DefaultValue("0") @QueryParam("page") int page,
                                                     @DefaultValue("100") @QueryParam("perPage") int perPage,
                                                     @DefaultValue("") @QueryParam("searchString") String searchString,
                                                     @DefaultValue("true") @QueryParam("ascending") boolean ascending) {
        PageRequestDto requestDto = PageRequestDto.builder()
                .withPage(page)
                .withPerPage(perPage)
                .withSearchString(searchString)
                .withAscending(ascending)
                .build();
        return repositoryService.getRepositoryPage(requestDto);
    }

    @GET
    @Path("/{repositoryId}")
    public Response getRepositoryById(@PathParam("repositoryId") long id) {
        Optional<RepositoryDto> optionalDto = repositoryService.getOneItem(id);
        if (optionalDto.isPresent()) {
            return Response.ok(optionalDto.get()).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{repositoryId}")
    public Response updateRepositoryById(@Nonnull RepositoryDto dto, @PathParam("repositoryId") long id) {
        if (id == dto.getRepositoryId()) {
            return Response.status(Response.Status.OK).entity(repositoryService.update(dto)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/types")
    public RepositoryType[] getValidRepositoryTypes() {
        return RepositoryType.values();
    }
}
