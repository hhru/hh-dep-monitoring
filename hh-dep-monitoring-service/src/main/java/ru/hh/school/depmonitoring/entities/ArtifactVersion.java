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
@Table(name = "artifact_version")

public class ArtifactVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artifact_version_artifact_version_id_seq")
    @SequenceGenerator(
            name = "artifact_version_artifact_version_id_seq",
            allocationSize = 1,
            sequenceName = "artifact_version_artifact_version_id_seq"
    )
    @Column(name = "artifact_version_id")
    private Integer artifactVersionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artifact_id")
    private Artifact artifact;

    @Column(name = "version_major")
    private Integer versionMajor;

    @Column(name = "version_minor")
    private Integer versionMinor;

    @Column(name = "version_micro")
    private Integer versionMicro;

    @Column(name = "version")
    private String version;

    public ArtifactVersion() {
    }

    public ArtifactVersion(Artifact artifact, Integer versionMajor, Integer versionMinor, Integer versionMicro, String version) {
        this.artifact = artifact;
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionMicro = versionMicro;
        this.version = version;
    }

    public Integer getArtifactVersionId() {
        return artifactVersionId;
    }

    public void setArtifactVersionId(Integer artifactVersionId) {
        this.artifactVersionId = artifactVersionId;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }

    public Integer getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(Integer versionMajor) {
        this.versionMajor = versionMajor;
    }

    public Integer getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(Integer versionMinor) {
        this.versionMinor = versionMinor;
    }

    public Integer getVersionMicro() {
        return versionMicro;
    }

    public void setVersionMicro(Integer versionMicro) {
        this.versionMicro = versionMicro;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArtifactVersion artifactVersion = (ArtifactVersion) o;
        return Objects.equals(artifactVersionId, artifactVersion.artifactVersionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactVersionId);
    }
}
