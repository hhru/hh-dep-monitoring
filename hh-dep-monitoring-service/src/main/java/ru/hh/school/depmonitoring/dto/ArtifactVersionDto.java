package ru.hh.school.depmonitoring.dto;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ArtifactVersionDto {

    @NotNull
    private Integer artifactVersionId;

    @NotNull
    private ArtifactDto artifact;

    @Nullable
    private Integer versionMajor;

    @Nullable
    private Integer versionMinor;

    @Nullable
    private Integer versionMicro;

    @Nullable
    private String version;

    public Integer getArtifactVersionId() {
        return artifactVersionId;
    }

    public void setArtifactVersionId(Integer artifactVersionId) {
        this.artifactVersionId = artifactVersionId;
    }

    public ArtifactDto getArtifact() {
        return artifact;
    }

    public void setArtifact(ArtifactDto artifact) {
        this.artifact = artifact;
    }

    @Nullable
    public Integer getVersionMajor() {
        return versionMajor;
    }

    public void setVersionMajor(@Nullable Integer versionMajor) {
        this.versionMajor = versionMajor;
    }

    @Nullable
    public Integer getVersionMinor() {
        return versionMinor;
    }

    public void setVersionMinor(@Nullable Integer versionMinor) {
        this.versionMinor = versionMinor;
    }

    @Nullable
    public Integer getVersionMicro() {
        return versionMicro;
    }

    public void setVersionMicro(@Nullable Integer versionMicro) {
        this.versionMicro = versionMicro;
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    public void setVersion(@Nullable String version) {
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
        ArtifactVersionDto dto = (ArtifactVersionDto) o;
        return Objects.equals(artifactVersionId, dto.artifactVersionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactVersionId);
    }
}
