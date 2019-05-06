package ru.hh.school.depmonitoring.rs;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dto.EventDto;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.entities.Event;
import ru.hh.school.depmonitoring.service.mapper.EventMapper;
import ru.hh.school.depmonitoring.utils.DBUtils;

import javax.inject.Inject;
import java.util.List;
import javax.ws.rs.core.GenericType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class EventResourceTest extends DepMonitoringTestBase {

    @Inject
    private DBUtils dbUtils;

    @Inject
    private EventMapper eventMapper;

    @Test
    public void getAllEventsTest() {
        Event controlEvent = dbUtils.addEvent();
        List<EventDto> resultDtosList = target("/events/")
                .request()
                .get()
                .readEntity(new GenericType<List<EventDto>>() {
                });
        assertFalse(resultDtosList.isEmpty());

        Event event = eventMapper.toEntity(resultDtosList.get(0));
        assertNotNull(event.getEventId());
        assertEventEquals(controlEvent, event);
    }

    @Test
    public void getAllEventsPage() {
        Event controlEvent = dbUtils.addEvent();
        PageDto<EventDto> resultDtosList = target("/events/page")
                .request()
                .get()
                .readEntity(new GenericType<PageDto<EventDto>>() {
                });
        assertFalse(resultDtosList.getItems().isEmpty());

        Event event = eventMapper.toEntity(resultDtosList.getItems().get(0));
        assertNotNull(event.getEventId());
        assertEventEquals(controlEvent, event);

    }

    public void assertEventEquals(Event controlEvent , Event event) {
        assertEquals(controlEvent.getEventId(), event.getEventId());
        assertEquals(controlEvent.getType(), event.getType());
        assertEquals(controlEvent.getCreatedAt(), event.getCreatedAt());
        assertEquals(controlEvent.getDescription(), event.getDescription());

    }
}
