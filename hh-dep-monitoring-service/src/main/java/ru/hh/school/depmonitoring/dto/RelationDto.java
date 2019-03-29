package ru.hh.school.depmonitoring.dto;

import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;

import java.io.Serializable;
import java.util.Objects;

public class RelationDto implements Serializable {

    private final Integer relationId;

    private final Long repositoryFromId;

    private final String repositoryFromName;

    private final Boolean repositoryFromHasRelations;

    private final Long repositoryToId;

    private final String repositoryToName;

    private final Boolean repositoryToHasRelations;

    private final RepositoryRelationPriority priority;

    private final String description;

    public RelationDto() {
        this(builder());
    }

    private RelationDto(RelationDtoBuilder builder) {
        this.relationId = builder.relationId;
        this.repositoryFromId = builder.repositoryFromId;
        this.repositoryFromName = builder.repositoryFromName;
        this.repositoryFromHasRelations = builder.repositoryFromHasRelations;
        this.repositoryToId = builder.repositoryToId;
        this.repositoryToName = builder.repositoryToName;
        this.repositoryToHasRelations = builder.repositoryToHasRelations;
        this.priority = builder.priority;
        this.description = builder.description;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public Long getRepositoryFromId() {
        return repositoryFromId;
    }

    public String getRepositoryFromName() {
        return repositoryFromName;
    }

    public Boolean isRepositoryFromHasRelations() {
        return repositoryFromHasRelations;
    }

    public Long getRepositoryToId() {
        return repositoryToId;
    }

    public String getRepositoryToName() {
        return repositoryToName;
    }

    public Boolean isRepositoryToHasRelations() {
        return repositoryToHasRelations;
    }

    public RepositoryRelationPriority getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }

    public static RelationDtoBuilder builder() {
        return new RelationDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelationDto)) {
            return false;
        }
        RelationDto relationDto = (RelationDto) o;
        return Objects.equals(relationId, relationDto.getRelationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationId);
    }

    public static final class RelationDtoBuilder {
        private Integer relationId;
        private Long repositoryFromId;
        private String repositoryFromName;
        private Boolean repositoryFromHasRelations;
        private Long repositoryToId;
        private String repositoryToName;
        private Boolean repositoryToHasRelations;
        private RepositoryRelationPriority priority;
        private String description;

        private RelationDtoBuilder() {
        }

        public RelationDtoBuilder withRelationId(Integer relationId) {
            this.relationId = relationId;
            return this;
        }

        public RelationDtoBuilder withRepositoryFromId(Long repositoryFromId) {
            this.repositoryFromId = repositoryFromId;
            return this;
        }

        public RelationDtoBuilder withRepositoryFromName(String repositoryFromName) {
            this.repositoryFromName = repositoryFromName;
            return this;
        }

        public RelationDtoBuilder withRepositoryFromHasRelations(Boolean repositoryFromHasRelations) {
            this.repositoryFromHasRelations = repositoryFromHasRelations;
            return this;
        }

        public RelationDtoBuilder withRepositoryToId(Long repositoryToId) {
            this.repositoryToId = repositoryToId;
            return this;
        }

        public RelationDtoBuilder withRepositoryToName(String repositoryToName) {
            this.repositoryToName = repositoryToName;
            return this;
        }

        public RelationDtoBuilder withRepositoryToHasRelations(Boolean repositoryToHasRelations) {
            this.repositoryToHasRelations = repositoryToHasRelations;
            return this;
        }

        public RelationDtoBuilder withPriority(RepositoryRelationPriority priority) {
            this.priority = priority;
            return this;
        }

        public RelationDtoBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RelationDto build() {
            return new RelationDto(this);
        }
    }
}
