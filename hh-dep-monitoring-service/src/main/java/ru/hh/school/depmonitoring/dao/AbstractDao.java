package ru.hh.school.depmonitoring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dto.PageRequestDto;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        if (!isBlank(pageRequestDto.getSearchString())) {
            String searchString = getContainsString(pageRequestDto.getSearchString());
            cq.where(cb.like(cb.lower(root.get(getDefaultFilterField())), searchString.toLowerCase()));
        }

        for (PageRequestDto.PageSort pageSort : pageRequestDto.getPageSortList()) {
            if (getFieldNames().contains(pageSort.getProperty())) {
                var orderExpression = root.get(pageSort.getProperty());
                cq.orderBy(pageSort.isAscending() ?
                        cb.asc(orderExpression) :
                        cb.desc(orderExpression));
            }
        }
        return getSession().createQuery(cq)
                .setFirstResult(offsetIndex)
                .setMaxResults(perPage)
                .getResultList();
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

    protected Set<String> getFieldNames() {
        return Arrays.stream(this.clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet());
    }

    protected String getDefaultFilterField() {
        return null;
    }
}
