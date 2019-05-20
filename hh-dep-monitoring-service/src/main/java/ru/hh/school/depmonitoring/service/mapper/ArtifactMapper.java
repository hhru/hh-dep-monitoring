package ru.hh.school.depmonitoring.service.mapper;

import javax.annotation.Nonnull;

import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.ArtifactDto;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.Repository;

public class ArtifactMapper implements Mapper<ArtifactDto, Artifact> {
    private RepositoryDao repositoryDao;

    public ArtifactMapper(RepositoryDao repositoryDao) {
        this.repositoryDao = repositoryDao;
    }

    public ArtifactDto toDto(@Nonnull Artifact entity) {
        return ArtifactDto.builder()
                .withArtifactId(entity.getArtifactId())
                .withArtifactName(entity.getArtifactName())
                .withGroupName(entity.getGroupName())
                .withRepositoryId(entity.getRepository() != null ? entity.getRepository().getRepositoryId() : null)
                .build();
    }

    public Artifact toEntity(@Nonnull ArtifactDto dto) {
        Artifact entity = new Artifact();

        if (dto.getRepositoryId() != null) {
            Repository repository = repositoryDao
                    .findOne(dto.getRepositoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Repository with ID " + dto.getRepositoryId() + " not found"));
            entity.setRepository(repository);
        }

        entity.setArtifactId(dto.getArtifactId());
        entity.setArtifactName(dto.getArtifactName());
        entity.setGroupName(dto.getGroupName());

        return entity;
    }
}
