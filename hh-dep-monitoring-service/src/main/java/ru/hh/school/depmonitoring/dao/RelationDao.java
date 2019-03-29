package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;

import java.util.List;

public interface RelationDao extends Dao<Relation, Integer> {

    List<Relation> findRelationsByMainRepositoryId(Repository repository);

    List<Relation> findRelationsByDependentRepositoryId(Repository repository);

}
