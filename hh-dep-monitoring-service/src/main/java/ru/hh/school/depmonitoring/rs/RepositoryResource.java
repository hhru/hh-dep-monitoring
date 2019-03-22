package ru.hh.school.depmonitoring.rs;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.service.RepositoryService;

@Named
@Singleton
@Path("/repository")
public class RepositoryResource {
    private RepositoryService service;

    public RepositoryResource(RepositoryService service) {
        this.service = service;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public PageDto<RepositoryDto> getAllRepositories(@DefaultValue("0") @QueryParam("page") int page,
                                                     @DefaultValue("10") @QueryParam("perPage") int perPage) {
        return getPage(service.getFullList(), page, perPage);
    }

    @GET
    @Path("/{repositoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRepositoryById(@PathParam("repositoryId") long id) {
        Optional<RepositoryDto> optionalDto = service.getOneItem(id);
        if (optionalDto.isPresent()) {
            return Response.ok(optionalDto.get()).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{repositoryId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRepositoryById(RepositoryDto dto, @PathParam("repositoryId") long id) {
        if (dto != null && id == dto.getRepositoryId()) {
            this.service.update(dto);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    private static PageDto<RepositoryDto> getPage(List<RepositoryDto> list, int page, int perPage) {
        if (list == null) {
            list = Collections.emptyList();
        }
        int found = list.size();
        int pages = 0;
        if (perPage > 0) {
            pages = found / perPage;
            if (found % perPage > 0) {
                pages++;
            }
        } else {
            perPage = 0;
        }
        int startIndex = perPage * page;
        int stopIndex = Math.min(startIndex + perPage, found);
        RepositoryDto[] items = list.subList(startIndex, stopIndex).toArray(new RepositoryDto[0]);
        return PageDto.<RepositoryDto>builder()
                .withFound(found)
                .withItems(items)
                .withPage(page)
                .withPages(pages)
                .withPerPage(perPage)
                .build();
    }

}
