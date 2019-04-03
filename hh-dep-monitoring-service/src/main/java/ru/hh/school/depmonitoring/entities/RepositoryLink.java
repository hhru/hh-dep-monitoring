package ru.hh.school.depmonitoring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.EnumType;
import javax.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "repository_link")
public class RepositoryLink {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repository_link_repository_link_id_seq")
    @SequenceGenerator(name = "repository_link_repository_link_id_seq", allocationSize = 1, sequenceName = "repository_link_repository_link_id_seq")
    @Column(name = "repository_link_id")
    private Integer repositoryLinkId;

    @NotNull
    @Column(name = "repository_id")
    private Long repositoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "link_type")
    @NotNull
    private RepositoryLinkType linkType;

    /**
     * varchar(255)
     */
    @NotNull
    @Column(name = "link_url")
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
        RepositoryLink repositoryLink = (RepositoryLink) o;
        return Objects.equals(repositoryLinkId, repositoryLink.getRepositoryLinkId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(repositoryLinkId);
    }

}
