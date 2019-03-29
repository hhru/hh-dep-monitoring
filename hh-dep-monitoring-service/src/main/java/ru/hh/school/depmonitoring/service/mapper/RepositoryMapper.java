package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;

public class RepositoryMapper implements Mapper<RepositoryDto, Repository> {

    public Repository toEntity(RepositoryDto dto) {
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

        return entity;
    }

    public RepositoryDto toDto(Repository entity) {
        if (entity == null) {
            return null;
        }

        RepositoryDto dto = new RepositoryDto();
        dto.setRepositoryId(entity.getRepositoryId());
        dto.setName(entity.getName());
        dto.setHtmlUrl(entity.getHtmlUrl());
        dto.setDescription(entity.getDescription());
        dto.setArchived(entity.isArchived());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());


        dto.setHasRelatedTo(entity.getRelatedTo() != null && !entity.getRelatedTo().isEmpty());
        dto.setHasRelatedFrom(entity.getRelatedFrom() != null && !entity.getRelatedFrom().isEmpty());

        return dto;
    }


}