package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryType;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class GHRepositoryMapper implements Mapper<GHRepositoryDto, Repository> {
    @Override
    public GHRepositoryDto toDto(Repository entity) {
        if (entity == null) {
            return null;
        }
        return GHRepositoryDto.builder()
                .withRepositoryId(entity.getRepositoryId())
                .withName(entity.getName())
                .withHtmlUrl(entity.getHtmlUrl())
                .withDescription(entity.getDescription())
                .withArchived(entity.isArchived())
                .withActive(entity.isActive())
                .withCreatedAt(entity.getCreatedAt())
                .withUpdatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public Repository toEntity(GHRepositoryDto dto) {
        if (dto == null) {
            return null;
        }
        Repository entity = new Repository();
        entity.setRepositoryId(dto.getRepositoryId());
        entity.setName(dto.getName());
        entity.setHtmlUrl(dto.getHtmlUrl());
        entity.setDescription(dto.getDescription());
        entity.setArchived(dto.isArchived());
        entity.setActive(dto.isActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setRepositoryType(RepositoryType.OTHER);
        return entity;
    }
}
