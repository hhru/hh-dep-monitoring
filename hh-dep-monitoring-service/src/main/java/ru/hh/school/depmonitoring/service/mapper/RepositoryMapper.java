package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.Coverage;
import ru.hh.school.depmonitoring.entities.CoverageSourceType;
import ru.hh.school.depmonitoring.entities.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class RepositoryMapper implements Mapper<RepositoryDto, Repository> {

    private final RepositoryLinkMapper repositoryLinkMapper;
    private final EventDao eventDao;
    private final CoverageDao coverageDao;
    private final ArtifactMapper artifactMapper;

    public RepositoryMapper(RepositoryLinkMapper repositoryLinkMapper, EventDao eventDao, ArtifactMapper artifactMapper, CoverageDao coverageDao) {
        this.repositoryLinkMapper = repositoryLinkMapper;
        this.eventDao = eventDao;
        this.artifactMapper = artifactMapper;
        this.coverageDao = coverageDao;
    }

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
        entity.setRepositoryType(dto.getRepositoryType());

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
        dto.setRepositoryType(entity.getRepositoryType());

        dto.setLinkUrls(repositoryLinkMapper.toDto(entity.getLinkUrls()));
        dto.setArtifacts(artifactMapper.toDto(entity.getArtifacts()));
        dto.setLastEvent(eventDao.getLastEventForRepository(entity.getRepositoryId()).orElse(null));
        Optional<Coverage> coverageOptional = coverageDao.findLastForRepositoryByType(entity.getRepositoryId(), CoverageSourceType.SONARCLOUD);
        BigDecimal coverage = coverageOptional
                .map(Coverage::getCoverage)
                .map(BigDecimal::new)
                .map(x -> x.setScale(1, RoundingMode.HALF_UP))
                .orElse(null);
        dto.setCoverage(coverage);

        dto.setHasRelatedTo(entity.getRelatedTo() != null && !entity.getRelatedTo().isEmpty());
        dto.setHasRelatedFrom(entity.getRelatedFrom() != null && !entity.getRelatedFrom().isEmpty());

        return dto;
    }
}
