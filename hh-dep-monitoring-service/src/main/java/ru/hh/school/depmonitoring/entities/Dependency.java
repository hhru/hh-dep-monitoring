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
@Table(name = "dependency")
public class Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependency_dependency_id_seq")
    @SequenceGenerator(name = "dependency_dependency_id_seq", allocationSize = 1, sequenceName = "dependency_dependency_id_seq")
    @Column(name = "dependency_id")
    private Integer dependencyId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repository_id")
    private Repository repository;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artifact_version_id")
    private ArtifactVersion artifactVersion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_dependency_id")
    private Dependency parentDependency;

    public Dependency() {
    }

    public Dependency(Repository repository, ArtifactVersion artifactVersion, Dependency parentDependency) {
        this.repository = repository;
        this.artifactVersion = artifactVersion;
        this.parentDependency = parentDependency;
    }

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public ArtifactVersion getArtifactVersion() {
        return artifactVersion;
    }

    public void setArtifactVersion(ArtifactVersion artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    public Dependency getParentDependency() {
        return parentDependency;
    }

    public void setParentDependency(Dependency parentDependency) {
        this.parentDependency = parentDependency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dependency dependency = (Dependency) o;
        return Objects.equals(dependencyId, dependency.dependencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dependencyId);
    }
}
