package ru.hh.school.depmonitoring.service.mapper;

import javax.annotation.Nonnull;

import ru.hh.school.depmonitoring.dao.ArtifactDao;
import ru.hh.school.depmonitoring.dto.ArtifactDto;
import ru.hh.school.depmonitoring.dto.EventDto;
import ru.hh.school.depmonitoring.entities.Event;

public class EventMapper implements Mapper<EventDto, Event> {
    private final ArtifactDao artifactDao;
    private final ArtifactMapper artifactMapper;

    public EventMapper(ArtifactDao artifactDao, ArtifactMapper artifactMapper) {
        this.artifactDao = artifactDao;
        this.artifactMapper = artifactMapper;
    }

    public EventDto toDto(@Nonnull Event entity) {
        ArtifactDto artifact = entity.getArtifactId() == null ? null : artifactMapper.toDto(
                artifactDao.findOne(
                        entity.getArtifactId())
                        .orElseThrow(() -> new IllegalArgumentException("Artifact( with ID "
                                + entity.getArtifactId()
                                + " not found")));


        return EventDto.builder()
                .withArtifact(artifact)
                .withEventId(entity.getEventId())
                .withCreatedAt(entity.getCreatedAt())
                .withRepositoryId(entity.getRepositoryId())
                .withType(entity.getType())
                .withDescription(entity.getDescription())
                .build();
    }

    public Event toEntity(@Nonnull EventDto dto) {
        Event entity = new Event();

        entity.setEventId(dto.getEventId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setRepositoryId(dto.getRepositoryId());
        entity.setArtifactId(dto.getArtifact() == null ? null : dto.getArtifact().getArtifactId());
        entity.setType(dto.getType());
        entity.setDescription(dto.getDescription());

        return entity;
    }

}
