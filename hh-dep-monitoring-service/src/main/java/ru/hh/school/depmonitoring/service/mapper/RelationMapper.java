package ru.hh.school.depmonitoring.service.mapper;

import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class RelationMapper implements Mapper<RelationDto, Relation> {
    private RepositoryDao repositoryDao;

    public RelationMapper(RepositoryDao repositoryDao) {
        this.repositoryDao = repositoryDao;
    }

    @Override
    public RelationDto toDto(Relation entity) {
        if (entity == null) {
            return null;
        }
        return RelationDto.builder()
                .withRelationId(entity.getRelationId())
                .withRepositoryFromId(entity.getRepositoryFrom().getRepositoryId())
                .withRepositoryFromName(entity.getRepositoryFrom().getName())
                .withRepositoryFromHasRelations(entity.getRepositoryFrom().getRelatedFrom() != null &&
                        !entity.getRepositoryFrom().getRelatedFrom().isEmpty())
                .withRepositoryToId(entity.getRepositoryTo().getRepositoryId())
                .withRepositoryToName(entity.getRepositoryTo().getName())
                .withRepositoryToHasRelations(entity.getRepositoryFrom().getRelatedTo() != null &&
                        !entity.getRepositoryTo().getRelatedTo().isEmpty())
                .withPriority(entity.getPriority())
                .withDescription(entity.getDescription())
                .build();
    }

    @Override
    public Relation toEntity(RelationDto dto) {
        if (dto == null) {
            return null;
        }
        Relation entity = new Relation();
        entity.setRepositoryFrom(repositoryDao.findOne(dto.getRepositoryFromId()).orElseThrow());
        entity.setRepositoryTo(repositoryDao.findOne(dto.getRepositoryToId()).orElseThrow());
        entity.setPriority(dto.getPriority());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
