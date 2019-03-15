package ru.hh.school.depmonitoring.dao;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findOne(int id);

    List<T> findAll();

    void create(@NotNull T entity);

    void update(@NotNull T entity);

    void delete(@NotNull T entity);

    void deleteById(int entityId);

}
