package ru.hh.school.depmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "repository")
public class Repository {

    @Id
    @Column(name = "repository_id")
    private Long repositoryId;

    /**
     * varchar(110)
     */
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * varchar(255)
     */
    @NotNull
    @Column(name = "html_url")
    private String htmlUrl;

    /**
     * varchar(500)
     */
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_archived")
    private boolean isArchived;

    @NotNull
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * timestamptz
     */
    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * timestamptz
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "repositoryFrom")
    private Set<Relation> relatedTo;

    @OneToMany(mappedBy = "repositoryTo")
    private Set<Relation> relatedFrom;

    @OneToMany(mappedBy = "repositoryId", fetch = FetchType.LAZY)
    private Set<RepositoryLink> linkUrls;

    public Long getRepositoryId() {
        return repositoryId;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<Relation> getRelatedTo() {
        return relatedTo;
    }

    public Set<Relation> getRelatedFrom() {
        return relatedFrom;
    }

    public Set<RepositoryLink> getLinkUrls() {
        return linkUrls;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        Repository repository = (Repository) o;
        return Objects.equals(repositoryId, repository.repositoryId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(repositoryId);
    }
}
