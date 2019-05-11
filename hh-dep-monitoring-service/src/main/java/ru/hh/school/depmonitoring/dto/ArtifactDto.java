package ru.hh.school.depmonitoring.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

public class ArtifactDto implements Serializable {
    @NotNull
    private Integer artifactId;

    @NotNull
    private String artifactName;

    @NotNull
    private String groupName;

    private Long repositoryId;

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(@Nonnull Integer artifactId) {
        this.artifactId = artifactId;
    }

    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(@Nonnull String artifactName) {
        this.artifactName = artifactName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(@Nonnull String groupName) {
        this.groupName = groupName;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public ArtifactDto() {
        this(builder());
    }

    private ArtifactDto(ArtifactDtoBuilder builder) {
        this.artifactId = builder.artifactId;
        this.artifactName = builder.artifactName;
        this.groupName = builder.groupName;
        this.repositoryId = builder.repositoryId;
    }

    public static ArtifactDtoBuilder builder() {
        return new ArtifactDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArtifactDto that = (ArtifactDto) o;
        return Objects.equals(artifactId, that.artifactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactId);
    }

    public static final class ArtifactDtoBuilder {
        private Integer artifactId;
        private String artifactName;
        private String groupName;
        private Long repositoryId;

        private ArtifactDtoBuilder() {

        }

        public ArtifactDtoBuilder withArtifactId(Integer artifactId) {
            this.artifactId = artifactId;
            return this;
        }

        public ArtifactDtoBuilder withArtifactName(String artifactName) {
            this.artifactName = artifactName;
            return this;
        }

        public ArtifactDtoBuilder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public ArtifactDtoBuilder withRepositoryId(Long repositoryId) {
            this.repositoryId = repositoryId;
            return this;
        }

        public ArtifactDto build() {
            return new ArtifactDto(this);
        }

    }
}
