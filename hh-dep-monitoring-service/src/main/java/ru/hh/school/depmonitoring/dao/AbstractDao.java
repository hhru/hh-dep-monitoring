package ru.hh.school.depmonitoring.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dto.PageRequestDto;

/**
 * @inheritDoc
 */
public abstract class AbstractDao<T, I extends Serializable> implements Dao<T, I> {

    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDao(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public Optional<T> findOne(I id) {
        return Optional.ofNullable(getSession().get(clazz, id));
    }

    @Override
    public List<T> findAll() {
        return getSession()
                .createQuery("from " + clazz.getName(), clazz)
                .list();
    }

    @Override
    public List<T> findPage(@Nonnull PageRequestDto pageRequestDto) {
        int perPage = pageRequestDto.getPerPage();
        int offsetIndex = pageRequestDto.getPage() * perPage;
        return getSession()
                .createQuery("from " + clazz.getName(), clazz)
                .setFirstResult(offsetIndex)
                .setMaxResults(perPage)
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
    public void deleteById(@Nonnull I entityId) {
        Optional<T> entity = findOne(entityId);
        entity.ifPresent(this::delete);
    }

    @Override
    public int count() {
        return getSession()
                .createQuery("select count(*) from " + clazz.getName(), Long.class)
                .uniqueResult()
                .intValue();
    }

    protected static String getContainsString(String str) {
        if (str == null || str.isEmpty()) {
            return "%";
        }
        return String.format("%%%s%%", str);
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
