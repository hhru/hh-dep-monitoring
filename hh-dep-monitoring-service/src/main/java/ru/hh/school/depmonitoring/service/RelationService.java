package ru.hh.school.depmonitoring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.mapper.RelationMapper;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

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

    private Repository getRepositoryDate(long repositoryId) {
        return repositoryDao.findOne(repositoryId)
                .orElseThrow(() -> new IllegalArgumentException("Error in getting repository date for id = " + repositoryId));
    }

    @Transactional(readOnly = true)
    public List<RelationDto> getRelationsDependOn(long repositoryId) {
        Repository repository = getRepositoryDate(repositoryId);
        List<Relation> relationList = relationDao.findRelationsByMainRepositoryId(repository);
        return relationMapper.toDto(relationList);
    }

    @Transactional(readOnly = true)
    public List<RelationDto> getRelationsDependOut(long repositoryId) {
        Repository repository = getRepositoryDate(repositoryId);
        List<Relation> relationList =relationDao.findRelationsByDependentRepositoryId(repository);
        return relationMapper.toDto(relationList);
    }

    @Transactional
    public Relation insertRelation(RelationDto relationDto) {
        Relation relation = relationMapper.toEntity(relationDto);
        relationDao.create(relation);
        return relation;
    }

    @Transactional
    public boolean updateRelation(int relationId, RelationDto relationDto) {
        Optional<Relation> relationOptional = relationDao.findOne(relationId);
        if (relationOptional.isPresent()) {
            Relation relation = relationOptional.get();
            if (relationDto.getRepositoryFromId() != null &&
                    !relationDto.getRepositoryFromId().equals(relation.getRepositoryFrom().getRepositoryId())) {
                relation.setRepositoryFrom(getRepositoryDate(relationDto.getRepositoryFromId()));
            }
            if (relationDto.getRepositoryToId() != null &&
                    !relationDto.getRepositoryToId().equals(relation.getRepositoryTo().getRepositoryId())) {
                relation.setRepositoryTo(getRepositoryDate(relationDto.getRepositoryToId()));
            }
            if (relationDto.getPriority() != null && !relationDto.getPriority().equals(relation.getPriority())) {
                relation.setPriority(relationDto.getPriority());
            }
            if (relationDto.getDescription() != null && !relationDto.getDescription().equals(relation.getDescription())) {
                relation.setDescription(relationDto.getDescription());
            }
            relationDao.update(relation);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteRelation(int relationId) {
        relationDao.deleteById(relationId);
    }
}
