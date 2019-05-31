package ru.hh.school.depmonitoring.service.mapper;

import javax.annotation.Nonnull;

import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.DependencyDto;
import ru.hh.school.depmonitoring.entities.Dependency;

public class DependencyMapper implements Mapper<DependencyDto, Dependency> {
    private final RepositoryDao repositoryDao;
    private final DependencyDao dependencyDao;
    private final ArtefactVersionMapper artefactVersionMapper;

    public DependencyMapper(RepositoryDao repositoryDao, DependencyDao dependencyDao, ArtefactVersionMapper artefactVersionMapper) {
        this.dependencyDao = dependencyDao;
        this.repositoryDao = repositoryDao;
        this.artefactVersionMapper = artefactVersionMapper;
    }

    public DependencyDto toDto(@Nonnull Dependency entity) {
        DependencyDto dto = new DependencyDto();
        dto.setDependencyId(entity.getDependencyId());
        dto.setRepositoryId(entity.getRepository().getRepositoryId());
        dto.setArtifactVersion(artefactVersionMapper.toDto(entity.getArtifactVersion()));
        dto.setParentDependencyId(entity.getParentDependency() != null ? entity.getParentDependency().getDependencyId() : null);

        return dto;
    }

    public Dependency toEntity(@Nonnull DependencyDto dto) {
        Dependency entity = new Dependency();
        entity.setDependencyId(dto.getDependencyId());
        entity.setRepository(
                repositoryDao.findOne(dto.getRepositoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Repository with ID " + dto.getRepositoryId() + " not found"))
        );
        entity.setArtifactVersion(artefactVersionMapper.toEntity(dto.getArtifactVersion()));

        if (dto.getParentDependencyId() == null) {
            entity.setParentDependency(null);
        } else {
            entity.setParentDependency(
                    dependencyDao.findOne(dto.getParentDependencyId())
                            .orElseThrow(() -> new IllegalArgumentException("Dependency with ID " + dto.getParentDependencyId() + " not found"))
            );
        }

        return entity;
    }
}
