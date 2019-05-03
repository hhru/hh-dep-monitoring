package ru.hh.school.depmonitoring.service;

import javax.annotation.Nonnull;

import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.dto.EventDto;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.Event;
import ru.hh.school.depmonitoring.service.mapper.EventMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EventService {
    private final EventDao eventDao;
    private final EventMapper eventMapper;

    public EventService(EventDao eventDao, EventMapper eventMapper) {
        this.eventDao = eventDao;
        this.eventMapper = eventMapper;
    }

    @Transactional(readOnly = true)
    public List<EventDto> getFullList() {
        return eventMapper.toDto(eventDao.findAll());
    }

    @Transactional(readOnly = true)
    public PageDto<EventDto> getEventPage(@Nonnull PageRequestDto pageRequestDto) {
        PageDto.PageDtoBuilder<EventDto> builder = PageDto.builder();
        if (pageRequestDto.getPerPage() <= 0 || pageRequestDto.getPage() < 0) {
            throw new IllegalArgumentException("Illegal pageRequestDto parameters");
        }
        List<EventDto> repositoryDtos = eventDao.findPage(pageRequestDto).stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
        builder = builder.withItems(repositoryDtos);
        int found = eventDao.count();
        int pages = (int) Math.ceil((double) found / pageRequestDto.getPerPage());
        return builder.withFound(found)
                .withPage(pageRequestDto.getPage())
                .withPerPage(pageRequestDto.getPerPage())
                .withPages(pages)
                .build();
    }

    @Transactional
    public EventDto insertEvent(@Nonnull EventDto eventDto) {
        Event event = eventMapper.toEntity(eventDto);
        eventDao.create(event);
        return eventMapper.toDto(event);
    }
}
