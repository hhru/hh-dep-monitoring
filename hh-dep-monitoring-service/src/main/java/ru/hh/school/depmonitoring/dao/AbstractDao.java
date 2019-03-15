package ru.hh.school.depmonitoring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public Optional<T> findOne(int id) {
        return Optional.ofNullable(getSession().get(clazz, id));
    }

    public List<T> findAll() {
        return getSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(@NotNull T entity) {
        getSession().persist(entity);
    }

    public void update(@NotNull T entity) {
        getSession().merge(entity);
    }

    public void delete(@NotNull T entity) {
        getSession().delete(entity);
    }

    public void deleteById(int entityId) {
        Optional<T> entity = findOne(entityId);
        entity.ifPresent(this::delete);
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
