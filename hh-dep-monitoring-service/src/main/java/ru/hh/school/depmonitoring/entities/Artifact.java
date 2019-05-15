package ru.hh.school.depmonitoring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "artifact")
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artifact_artifact_id_seq")
    @SequenceGenerator(name = "artifact_artifact_id_seq", allocationSize = 1, sequenceName = "artifact_artifact_id_seq")
    @Column(name = "artifact_id")
    private Integer artifactId;

    @Column(name = "artifact_name")
    private String artifactName;

    @Column(name = "group_name")
    private String groupName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id")
    private Repository repository;

    public Artifact() {
    }

    public Artifact(String artifactName, String groupName, Repository repository) {
        this.artifactName = artifactName;
        this.groupName = groupName;
        this.repository = repository;
    }

    public Integer getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(Integer artifactId) {
        this.artifactId = artifactId;
    }

    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artifact artifact = (Artifact) o;
        return Objects.equals(artifactId, artifact.artifactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactId);
    }
}
