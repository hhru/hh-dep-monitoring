package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.entities.Coverage;
import ru.hh.school.depmonitoring.entities.CoverageSourceType;

import java.util.Optional;

public interface CoverageDao extends Dao<Coverage, Integer> {
    Optional<Coverage> findLastForRepositoryByType(long repositoryId, CoverageSourceType type);
}
