package ru.hh.school.depmonitoring.dao;

import ru.hh.school.depmonitoring.dto.PageRequestDto;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/*
 *   @param <T> для передачи класса сущности
 *   @param <I> для передачи класса первичного ключа сущности
 * */
public interface Dao<T, I extends Serializable> {

    Optional<T> findOne(I id);

    List<T> findAll();

    List<T> findPage(@Nonnull PageRequestDto pageRequestDto);

    Expression<String> changeExpression(CriteriaBuilder cb, Expression<String> expression, String property);

    void create(@Nonnull T entity);

    void update(@Nonnull T entity);

    void delete(@Nonnull T entity);

    void deleteById(@Nonnull I entityId);

    int count();

}
