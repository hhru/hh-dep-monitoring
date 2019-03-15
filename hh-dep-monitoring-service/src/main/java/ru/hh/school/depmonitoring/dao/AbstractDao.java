package ru.hh.school.depmonitoring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/** @inheritDoc */
public abstract class AbstractDao<T, I> implements Dao<T, I> {

    private final Class<T> clazz;
    private final Class<I> idClazz;
    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory, Class<T> clazz, Class<I> idClazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
        this.idClazz = idClazz;
    }

    @Override
    public Optional<T> findOne(I id) {
        return Optional.ofNullable(getSession().get(clazz, idClazz));
    }

    @Override
    public List<T> findAll() {
        return getSession()
                .createQuery("from " + clazz.getName(), clazz)
                .list();
    }

    @Override
    public void create(@Nonnull T entity) {
        getSession().persist(entity);
    }

    @Override
    public void update(@Nonnull T entity) {
        getSession().merge(entity);
    }

    @Override
    public void delete(@Nonnull T entity) {
        getSession().delete(entity);
    }

    @Override
    public void deleteById(I entityId) {
        Optional<T> entity = findOne(entityId);
        entity.ifPresent(this::delete);
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
