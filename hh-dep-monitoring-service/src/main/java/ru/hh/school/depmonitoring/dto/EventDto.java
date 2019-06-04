package ru.hh.school.depmonitoring.dto;

import ru.hh.school.depmonitoring.entities.EventType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EventDto implements Serializable {

    private final Integer eventId;
    private final LocalDateTime createdAt;
    private final Long repositoryId;
    private final ArtifactDto artifact;
    private final EventType type;
    private final String description;

    public Integer getEventId() {
        return eventId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public ArtifactDto getArtifact() {
        return artifact;
    }

    public EventType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public EventDto() {
        this(builder());
    }

    private EventDto(EventDtoBuilder builder) {
        this.eventId = builder.eventId;
        this.createdAt = builder.createdAt;
        this.repositoryId = builder.repositoryId;
        this.artifact = builder.artifact;
        this.type = builder.type;
        this.description = builder.description;
    }

    public static EventDtoBuilder builder() {
        return new EventDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventDto eventDto = (EventDto) o;
        return Objects.equals(eventId, eventDto.getEventId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }


    public static final class EventDtoBuilder {
        private Integer eventId;
        private LocalDateTime createdAt;
        private Long repositoryId;
        private ArtifactDto artifact;
        private EventType type;
        private String description;

        private EventDtoBuilder() {
        }

        public EventDtoBuilder withEventId(Integer eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventDtoBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EventDtoBuilder withRepositoryId(Long repositoryId) {
            this.repositoryId = repositoryId;
            return this;
        }

        public EventDtoBuilder withArtifact(ArtifactDto artifact) {
            this.artifact = artifact;
            return this;
        }

        public EventDtoBuilder withType(EventType type) {
            this.type = type;
            return this;
        }

        public EventDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public EventDto build() {
            return new EventDto(this);
        }
    }
}
