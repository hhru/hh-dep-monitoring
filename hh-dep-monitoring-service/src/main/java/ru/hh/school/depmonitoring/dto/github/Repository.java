package ru.hh.school.depmonitoring.dto.github;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

public class Repository implements Serializable {
    private final long repositoryId;
    private final String name;
    private final URL htmlUrl;
    private final String description;
    private final boolean archived;
    private final boolean active;
    private final Date createdAt;
    private final Date updatedAt;

    public Repository() {
        this(builder());
    }

    private Repository(RepositoryBuilder repositoryBuilder) {
        this.repositoryId = repositoryBuilder.repositoryId;
        this.name = repositoryBuilder.name;
        this.htmlUrl = repositoryBuilder.htmlUrl;
        this.description = repositoryBuilder.description;
        this.archived = repositoryBuilder.archived;
        this.active = repositoryBuilder.active;
        this.createdAt = repositoryBuilder.createdAt;
        this.updatedAt = repositoryBuilder.updatedAt;
    }

    public static RepositoryBuilder builder() {
        return new RepositoryBuilder();
    }

    public long getRepositoryId() {
        return repositoryId;
    }

    public String getName() {
        return name;
    }

    public URL getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isActive() {
        return active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public static final class RepositoryBuilder {
        private long repositoryId;
        private String name;
        private URL htmlUrl;
        private String description;
        private boolean archived;
        private boolean active;
        private Date createdAt;
        private Date updatedAt;

        private RepositoryBuilder() {
        }

        public RepositoryBuilder withRepositoryId(long repositoryId) {
            this.repositoryId = repositoryId;
            return this;
        }

        public RepositoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RepositoryBuilder withHtmlUrl(URL htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        public RepositoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RepositoryBuilder withIsArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public RepositoryBuilder withIsActive(boolean active) {
            this.active = active;
            return this;
        }

        public RepositoryBuilder withCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RepositoryBuilder withUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Repository build() {
            return new Repository(this);
        }
    }
}
