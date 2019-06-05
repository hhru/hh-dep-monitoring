package ru.hh.school.depmonitoring.dto;

import ru.hh.school.depmonitoring.entities.RepositoryType;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ArtefactTreeDto {
    @NotNull
    private Long repositoryId;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @NotNull
    private RepositoryType repositoryType;

    @Nullable
    private List<DependencyDto> dependencies;

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public RepositoryType getRepositoryType() {
        return repositoryType;
    }

    public void setRepositoryType(RepositoryType repositoryType) {
        this.repositoryType = repositoryType;
    }

    @Nullable
    public List<DependencyDto> getDependencies() {
        return dependencies;
    }

    public void setDependencies(@Nullable List<DependencyDto> dependencies) {
        this.dependencies = dependencies;
    }
}
