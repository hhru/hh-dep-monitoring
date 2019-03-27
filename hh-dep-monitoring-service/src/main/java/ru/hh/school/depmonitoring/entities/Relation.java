package ru.hh.school.depmonitoring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "relation")
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relation_relation_id_seq")
    @SequenceGenerator(name = "relation_relation_id_seq", allocationSize = 1, sequenceName = "relation_relation_id_seq")
    @Column(name = "relation_id")
    private Integer relationId;

    @NotNull
    @Column(name = "repository_from_id")
    private Long repositoryFromId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_from_id", insertable = false, updatable = false)
    @JsonIgnore
    private Repository repositoryFrom;

    @NotNull
    @Column(name = "repository_to_id")
    private Long repositoryToId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_to_id", insertable = false, updatable = false)
    @JsonIgnore
    private Repository repositoryTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    @NotNull
    private RepositoryRelationPriority priority;

    /**
     * Text
     */
    @Column(name = "description")
    private String description;


    public Long getRepositoryFromId() {
        return repositoryFromId;
    }

    public void setRepositoryFromId(Long repositoryFromId) {
        this.repositoryFromId = repositoryFromId;
    }

    public Long getRepositoryToId() {
        return repositoryToId;
    }

    public void setRepositoryToId(Long repositoryToId) {
        this.repositoryToId = repositoryToId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Repository getRepositoryFrom() {
        return repositoryFrom;
    }

    public void setRepositoryFrom(Repository repositoryFrom) {
        this.repositoryFrom = repositoryFrom;
    }

    public Repository getRepositoryTo() {
        return repositoryTo;
    }

    public void setRepositoryTo(Repository repositoryTo) {
        this.repositoryTo = repositoryTo;
    }

    public RepositoryRelationPriority getPriority() {
        return priority;
    }

    public void setPriority(RepositoryRelationPriority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Relation relation = (Relation) o;
        return Objects.equals(relationId, relation.relationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relationId);
    }
}
