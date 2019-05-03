package ru.hh.school.depmonitoring.service.mapper;

import javax.annotation.Nonnull;

import ru.hh.school.depmonitoring.dto.EventDto;
import ru.hh.school.depmonitoring.entities.Event;

public class EventMapper implements Mapper<EventDto, Event> {

    public EventDto toDto(@Nonnull Event entity) {
        return EventDto.builder()
                .withEventId(entity.getEventId())
                .withCreatedAt(entity.getCreatedAt())
                .withRepositoryId(entity.getRepositoryId())
                .withArtifactId(entity.getArtifactId())
                .withType(entity.getType())
                .withDescription(entity.getDescription())
                .build();
    }

    public Event toEntity(@Nonnull EventDto dto) {
        Event entity = new Event();

        entity.setEventId(dto.getEventId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setRepositoryId(dto.getRepositoryId());
        entity.setArtifactId(dto.getArtifactId());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
