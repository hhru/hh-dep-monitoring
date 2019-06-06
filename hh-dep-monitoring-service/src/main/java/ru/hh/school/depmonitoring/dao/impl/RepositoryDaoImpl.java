package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import java.util.Optional;


@Named
@Singleton
public class RepositoryDaoImpl extends AbstractDao<Repository, Long> implements RepositoryDao {

    public RepositoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Repository.class);
    }

    @Override
    public Expression<String> changeExpression(CriteriaBuilder cb, Expression<String> expression, String property) {
        if (property.equals("name")) {
            return cb.lower(expression);
        }
        return expression;
    }

    @Override
    public Optional<Repository> findRepositoryByName(String repositoryName) {
        return getSession()
                .createQuery("from Repository where name = :repositoryName", Repository.class)
                .setParameter("repositoryName", repositoryName)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public int count(PageRequestDto pageRequestDto) {
        String searchString = getContainsString(pageRequestDto.getSearchString());
        return getSession()
                .createQuery("select count(*) from " + Repository.class.getName() +
                        " where lower(name) like lower(:searchString)", Long.class)
                .setParameter("searchString", searchString)
                .uniqueResult()
                .intValue();
    }


    @Override
    public String getDefaultFilterField() {
        return "name";
    }
}
