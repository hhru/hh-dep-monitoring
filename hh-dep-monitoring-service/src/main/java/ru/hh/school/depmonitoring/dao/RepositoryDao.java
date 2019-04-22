package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.Repository;

import java.util.Optional;

public interface RepositoryDao extends Dao<Repository, Long> {
    Optional<Repository> findRepositoryByName(String repositoryName);
    int count(PageRequestDto pageRequestDto);
}
