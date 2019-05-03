package ru.hh.school.depmonitoring.service;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.dto.EventDto;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.EventType;
import ru.hh.school.depmonitoring.utils.DBUtils;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class EventServiceTest extends DepMonitoringTestBase {
    @Inject
    private DBUtils dbUtils;

    @Inject
    private EventService eventService;

    @Inject
    private EventDao eventDao;

    @Test
    public void getEmptyAllEvents() {
        List<EventDto> result = eventService.getFullList();
        assertTrue(result.isEmpty());
    }

    @Test
    public void getEmptyPage() {
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .withPage(0)
                .withPerPage(10)
                .build();
        PageDto<EventDto> result = eventService.getEventPage(pageRequestDto);
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    public void getAllEvents() {
        dbUtils.addEvent();
        List<EventDto> result = eventService.getFullList();
        assertEquals(1, result.size());
    }

    @Test
    public void getPage() {
        dbUtils.addEvent();
        PageRequestDto pageRequestDto = PageRequestDto.builder()
                .withPage(0)
                .withPerPage(10)
                .build();
        PageDto<EventDto> result = eventService.getEventPage(pageRequestDto);
        assertEquals(1, result.getItems().size());
    }

    @Test
    public void addEvent() {
        dbUtils.fillRepositoryTable();
        EventDto newEvent = EventDto.builder()
                .withRepositoryId(1L)
                .withCreatedAt(LocalDateTime.now())
                .withType(EventType.CONFLICT)
                .withDescription("Description")
                .build();
        EventDto savedEvent = eventService.insertEvent(newEvent);

        assertEquals(1, (int) dbUtils.doInTransaction(() -> eventDao.count()));

        assertNotNull(savedEvent.getEventId());
        assertEquals(newEvent.getRepositoryId(), savedEvent.getRepositoryId());
        assertEquals(newEvent.getCreatedAt(), savedEvent.getCreatedAt());
        assertEquals(newEvent.getType(), savedEvent.getType());
        assertEquals(newEvent.getDescription(), savedEvent.getDescription());
    }
}
