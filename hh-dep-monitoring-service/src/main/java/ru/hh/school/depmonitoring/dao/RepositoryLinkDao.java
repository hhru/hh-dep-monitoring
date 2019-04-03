package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.entities.RepositoryLink;

import java.util.List;

public interface RepositoryLinkDao extends Dao<RepositoryLink, Integer> {
    List<RepositoryLink> findForRepository(long repositoryId);
}
