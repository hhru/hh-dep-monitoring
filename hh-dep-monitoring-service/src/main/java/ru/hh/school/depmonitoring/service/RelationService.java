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
        if (relationDto == null) {
            throw new IllegalArgumentException("Relation can`t be null");
        }

        Relation relation = relationMapper.toEntity(relationDto);
        checkCyclicRelations(relation.getRepositoryTo(), relationDto.getRepositoryFromId());
        searchForDuplicates(relation);
        relationDao.create(relation);
        return relationMapper.toDto(relation);
    }

    @Transactional
    public RelationDto updateRelation(int relationId, RelationDto relationDto) {
        Relation relation = relationDao.findOne(relationId)
                .orElseThrow(() -> new IllegalArgumentException("Relation with id = " + relationId + "not found!"));
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

    private void checkCyclicRelations(Repository repository, long firstId) {
        if (repository.getRepositoryId() == firstId) {
            throw new IllegalArgumentException("Repositories in relation must be not equal");
        }

        for (Relation relation : relationDao.findRelationsByMainRepositoryId(repository)) {
            if (relation.getRepositoryTo().getRepositoryId() == firstId) {
                throw new IllegalArgumentException("Cyclic relations detected");
            }
            checkCyclicRelations(relation.getRepositoryTo(), firstId);
        }
    }

    private void searchForDuplicates(Relation newRelation) {
        long newRepositoryToId = newRelation.getRepositoryTo().getRepositoryId();
        for (Relation relation : relationDao.findRelationsByMainRepositoryId(newRelation.getRepositoryFrom())) {
            if (relation.getRepositoryTo().getRepositoryId() == newRepositoryToId) {
                throw new IllegalArgumentException("Duplicate relation detected");
            }
        }
    }
}
