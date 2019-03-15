package ru.hh.school.depmonitoring.dao;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/*
 *   @param <T> для передачи класса сущности
 *   @param <I> для передачи класса первичного ключа сущности
 * */
public interface Dao<T, I> {

    Optional<T> findOne(I id);

    List<T> findAll();

    void create(@Nonnull T entity);

    void update(@Nonnull T entity);

    void delete(@Nonnull T entity);

    void deleteById(I entityId);

}
