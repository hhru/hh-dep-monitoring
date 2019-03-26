package ru.hh.school.depmonitoring.dto;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class RepositoryDto {

    @NotNull
    private Long repositoryId;

    @NotNull
    private String name;

    @NotNull
    private String htmlUrl;

    @Nullable
    private String description;

    @NotNull
    private boolean isArchived;

    @NotNull
    private boolean isActive;

    @NotNull
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime updatedAt;

    private boolean hasRelatedTo;

    private boolean hasRelatedFrom;

    public boolean getHasRelatedTo() {
        return hasRelatedTo;
    }

    public boolean getHasRelatedFrom() {
        return hasRelatedFrom;
    }

    public void setHasRelatedTo(boolean hasRelatedTo) {
        this.hasRelatedTo = hasRelatedTo;
    }

    public void setHasRelatedFrom(boolean hasRelatedFrom) {
        this.hasRelatedFrom = hasRelatedFrom;
    }

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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepositoryDto repositoryDto = (RepositoryDto) o;
        return Objects.equals(repositoryId, repositoryDto.getRepositoryId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(repositoryId);
    }

}
