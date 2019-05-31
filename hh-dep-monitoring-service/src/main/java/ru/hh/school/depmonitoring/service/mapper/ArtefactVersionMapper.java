package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dto.ArtifactVersionDto;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;

public class ArtefactVersionMapper implements Mapper<ArtifactVersionDto, ArtifactVersion> {
    private final ArtifactMapper artefactMapper;

    public ArtefactVersionMapper(ArtifactMapper artefactMapper) {
        this.artefactMapper = artefactMapper;
    }

    public ArtifactVersionDto toDto(ArtifactVersion entity) {
        ArtifactVersionDto dto = new ArtifactVersionDto();

        dto.setArtifactVersionId(entity.getArtifactVersionId());
        dto.setArtifact(artefactMapper.toDto(entity.getArtifact()));
        dto.setVersionMajor(entity.getVersionMajor());
        dto.setVersionMinor(entity.getVersionMinor());
        dto.setVersionMicro(entity.getVersionMicro());
        dto.setVersion(entity.getVersion());

        return dto;
    }

    public ArtifactVersion toEntity(ArtifactVersionDto dto) {
        ArtifactVersion entity = new ArtifactVersion();
        entity.setArtifactVersionId(dto.getArtifactVersionId());
        entity.setArtifact(artefactMapper.toEntity(dto.getArtifact()));
        entity.setVersionMajor(dto.getVersionMajor());
        entity.setVersionMinor(dto.getVersionMinor());
        entity.setVersionMicro(dto.getVersionMicro());
        entity.setVersion(dto.getVersion());

        return entity;
    }
}
