package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.Repository;

public interface RepositoryDao extends Dao<Repository, Long> {
   int count(PageRequestDto pageRequestDto);
}
