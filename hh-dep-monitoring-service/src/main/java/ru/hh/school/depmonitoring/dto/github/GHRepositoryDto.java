package ru.hh.school.depmonitoring.dto.github;

import ru.hh.school.depmonitoring.utils.DateUtils;

import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class GHRepositoryDto implements Serializable {
    private final long repositoryId;
    private final String name;
    private final String htmlUrl;
    private final String description;
    private final boolean archived;
    private final boolean active;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GHRepositoryDto() {
        this(builder());
    }

    private GHRepositoryDto(RepositoryBuilder repositoryBuilder) {
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

    public String getHtmlUrl() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static final class RepositoryBuilder {
        private long repositoryId;
        private String name;
        private String htmlUrl;
        private String description;
        private boolean archived;
        private boolean active;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

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
            if (htmlUrl != null) {
                this.htmlUrl = htmlUrl.toString();
            } else {
                this.htmlUrl = null;
            }
            return this;
        }

        public RepositoryBuilder withHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
            return this;
        }

        public RepositoryBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public RepositoryBuilder withArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public RepositoryBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public RepositoryBuilder withCreatedAt(Date createdAt) {
            this.createdAt = DateUtils.toLocalDateTime(createdAt);
            return this;
        }

        public RepositoryBuilder withCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RepositoryBuilder withUpdatedAt(Date updatedAt) {
            this.updatedAt = DateUtils.toLocalDateTime(updatedAt);
            return this;
        }

        public RepositoryBuilder withUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public GHRepositoryDto build() {
            return new GHRepositoryDto(this);
        }
    }
}
