package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.entities.RepositoryLink;

public class RepositoryLinkMapper implements Mapper<RepositoryLinkDto, RepositoryLink> {
    public RepositoryLink toEntity(RepositoryLinkDto dto) {
        if (dto == null) {
            return null;
        }
        RepositoryLink entity = new RepositoryLink();
        entity.setRepositoryLinkId(dto.getRepositoryLinkId());
        entity.setRepositoryId(dto.getRepositoryId());
        entity.setLinkType(dto.getLinkType());
        entity.setLinkUrl(dto.getLinkUrl());

        return entity;
    }

    public RepositoryLinkDto toDto(RepositoryLink entity) {
        if (entity == null) {
            return null;
        }

        RepositoryLinkDto dto = new RepositoryLinkDto();
        dto.setRepositoryLinkId(entity.getRepositoryLinkId());
        dto.setRepositoryId(entity.getRepositoryId());
        dto.setLinkType(entity.getLinkType());
        dto.setLinkUrl(entity.getLinkUrl());

        return dto;
    }
}
