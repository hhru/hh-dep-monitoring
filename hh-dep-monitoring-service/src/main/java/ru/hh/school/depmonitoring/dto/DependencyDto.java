package ru.hh.school.depmonitoring.dto;

import javax.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class DependencyDto {
    @NotNull
    private Integer dependencyId;

    @NotNull
    private Long repositoryId;

    @NotNull
    private ArtifactVersionDto artifactVersion;

    @Nullable
    private List<DependencyDto> children;

    @Nullable
    private Integer parentDependencyId;

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public ArtifactVersionDto getArtifactVersion() {
        return artifactVersion;
    }

    public void setArtifactVersion(ArtifactVersionDto artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    @Nullable
    public List<DependencyDto> getChildren() {
        return children;
    }

    public void setChildren(@Nullable List<DependencyDto> children) {
        this.children = children;
    }

    @Nullable
    public Integer getParentDependencyId() {
        return parentDependencyId;
    }

    public void setParentDependencyId(@Nullable Integer parentDependencyId) {
        this.parentDependencyId = parentDependencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DependencyDto that = (DependencyDto) o;
        return Objects.equals(repositoryId, that.repositoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repositoryId);
    }
}
