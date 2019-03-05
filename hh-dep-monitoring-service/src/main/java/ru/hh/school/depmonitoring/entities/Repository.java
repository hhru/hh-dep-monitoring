package ru.hh.school.depmonitoring.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "repository")
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repository_repository_id_seq")
    @SequenceGenerator(name = "repository_repository_id_seq", allocationSize = 1)
    @Column(name = "repository_id")
    private Integer repositoryId;

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

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
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
        Repository repository = (Repository) o;
        return Objects.equals(repositoryId, repository.repositoryId);
    }

    @Override
    public int hashCode() {
        int result = (repositoryId ^ (repositoryId >>> 32));
        return 31 * result + name.hashCode();
    }
}
