package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.entities.Event;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EventDao extends Dao<Event, Integer> {
    Optional<LocalDateTime> getLastEventForRepository(Long repositoryId);
}
