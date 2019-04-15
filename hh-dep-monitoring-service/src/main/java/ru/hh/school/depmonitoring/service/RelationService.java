package ru.hh.school.depmonitoring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.service.mapper.RelationMapper;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@Singleton
public class RelationService {
    private final RelationDao relationDao;
    private final RelationMapper relationMapper;
    private final RepositoryDao repositoryDao;

    public RelationService(RelationDao relationDao, RelationMapper relationMapper, RepositoryDao repositoryDao) {
        this.relationDao = relationDao;
        this.relationMapper = relationMapper;
        this.repositoryDao = repositoryDao;
    }

    private Repository getRepositoryData(long repositoryId) {
        return repositoryDao.findOne(repositoryId)
                .orElseThrow(() -> new IllegalArgumentException("Error in getting repository date for id = " + repositoryId));
    }

    private Map<RepositoryRelationPriority, List<RelationDto>> collect(List<Relation> relationList) {
        return relationList.stream()
                .map(relationMapper::toDto)
                .collect(Collectors.groupingBy(RelationDto::getPriority));
    }

    @Transactional(readOnly = true)
    public Map<RepositoryRelationPriority, List<RelationDto>> getRelationsDependOn(long repositoryId) {
        Repository repository = getRepositoryData(repositoryId);
        return collect(relationDao.findRelationsByMainRepositoryId(repository));
    }

    @Transactional(readOnly = true)
    public Map<RepositoryRelationPriority, List<RelationDto>> getRelationsDependOut(long repositoryId) {
        Repository repository = getRepositoryData(repositoryId);
        return collect(relationDao.findRelationsByDependentRepositoryId(repository));
    }

    @Transactional
    public RelationDto insertRelation(RelationDto relationDto) {
        Relation relation = relationMapper.toEntity(relationDto);
        relationDao.create(relation);
        return relationMapper.toDto(relation);
    }

    @Transactional
    public RelationDto updateRelation(int relationId, RelationDto relationDto) {
        Relation relation = relationDao.findOne(relationId)
                .orElseThrow(() -> new IllegalArgumentException("Relation with id = " + relationId + "not found!"));
        if (relationDto.getRepositoryFromId() != null &&
                !relationDto.getRepositoryFromId().equals(relation.getRepositoryFrom().getRepositoryId())) {
            relation.setRepositoryFrom(getRepositoryData(relationDto.getRepositoryFromId()));
        }
        if (relationDto.getRepositoryToId() != null &&
                !relationDto.getRepositoryToId().equals(relation.getRepositoryTo().getRepositoryId())) {
            relation.setRepositoryTo(getRepositoryData(relationDto.getRepositoryToId()));
        }
        if (relationDto.getPriority() != null && !relationDto.getPriority().equals(relation.getPriority())) {
            relation.setPriority(relationDto.getPriority());
        }
        if (relationDto.getDescription() != null && !relationDto.getDescription().equals(relation.getDescription())) {
            relation.setDescription(relationDto.getDescription());
        }
        relationDao.update(relation);
        return relationMapper.toDto(relation);
    }

    @Transactional
    public void deleteRelation(int relationId) {
        relationDao.deleteById(relationId);
    }
}
