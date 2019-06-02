package ru.hh.school.depmonitoring.dto;

import ru.hh.school.depmonitoring.entities.RepositoryType;

import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RepositoryDto implements Serializable {

    @NotNull
    private Long repositoryId;

    @NotNull
    private String name;

    @NotNull
    private String htmlUrl;

    @Nullable
    private String description;

    @NotNull
    private boolean archived;

    @NotNull
    private boolean active;

    @NotNull
    private LocalDateTime createdAt;

    @Nullable
    private LocalDateTime updatedAt;

    @NotNull
    private RepositoryType repositoryType;


    private boolean hasRelatedTo;

    private boolean hasRelatedFrom;

    @Nullable
    private LocalDateTime lastEvent;

    @NotNull
    private List<RepositoryLinkDto> linkUrls;

    private List<ArtifactDto> artifacts;

    @Nullable
    private BigDecimal coverage;

    @Nonnull
    public List<RepositoryLinkDto> getLinkUrls() {
        return Optional.ofNullable(linkUrls).orElseGet(List::of);
    }

    public void setLinkUrls(@Nonnull List<RepositoryLinkDto> linkUrls) {
        this.linkUrls = Objects.requireNonNull(linkUrls);
    }

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

    public LocalDateTime getLastEvent() {
        return lastEvent;
    }

    public void setLastEvent(LocalDateTime lastEvent) {
        this.lastEvent = lastEvent;
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
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public RepositoryType getRepositoryType() {
        return repositoryType;
    }

    public void setRepositoryType(RepositoryType repositoryType) {
        this.repositoryType = repositoryType;
    }

    public List<ArtifactDto> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactDto> artifacts) {
        this.artifacts = artifacts;
    }

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
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
        return Objects.hash(repositoryId, name, htmlUrl, description, archived, active, createdAt, updatedAt, hasRelatedTo, hasRelatedFrom);
    }
}
