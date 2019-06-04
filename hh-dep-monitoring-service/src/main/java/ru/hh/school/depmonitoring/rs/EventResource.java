package ru.hh.school.depmonitoring.rs;

import ru.hh.school.depmonitoring.dto.EventDto;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.service.EventService;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Singleton
@Path("/events")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {
    private final EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    @Path("/")
    public List<EventDto> getAll() {
        return eventService.getFullList();
    }

    @GET
    @Path("/page/")
    public PageDto<EventDto> getEventsPage(@DefaultValue("0") @QueryParam("page") int page,
                                                @DefaultValue("10") @QueryParam("perPage") int perPage) {
        PageRequestDto requestDto = PageRequestDto.builder()
                .withPage(page)
                .withPerPage(perPage)
                .build();
        return eventService.getEventPage(requestDto);
    }

    @GET
    @Path("/for-repository/{repositoryId}")
    public List<EventDto> getForRepository(@PathParam("repositoryId") long repositoryId) {
        return eventService.getForRepository(repositoryId);
    }

    @GET
    @Path("/for-repository/{repositoryId}/last/{eventsCount}")
    public List<EventDto> getEventsPageForRepository(
                                                @PathParam("repositoryId") long repositoryId,
                                                @PathParam("eventsCount") int eventsCount) {
        return eventService.getLastEventsForRepository(repositoryId, eventsCount);
    }
}
