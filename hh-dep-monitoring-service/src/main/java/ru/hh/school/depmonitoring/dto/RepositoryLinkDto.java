package ru.hh.school.depmonitoring.dto;

import ru.hh.school.depmonitoring.entities.RepositoryLinkType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class RepositoryLinkDto implements Serializable {

    @NotNull
    private Integer repositoryLinkId;

    @NotNull
    private Long repositoryId;

    @NotNull
    private RepositoryLinkType linkType;

    @NotNull
    private String linkUrl;

    public Integer getRepositoryLinkId() {
        return repositoryLinkId;
    }

    public void setRepositoryLinkId(Integer repositoryLinkId) {
        this.repositoryLinkId = repositoryLinkId;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public RepositoryLinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(RepositoryLinkType linkType) {
        this.linkType = linkType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RepositoryLinkDto repositoryLinkDto = (RepositoryLinkDto) o;
        return Objects.equals(repositoryLinkId, repositoryLinkDto.getRepositoryLinkId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(repositoryLinkId);
    }


}
