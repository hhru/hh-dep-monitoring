package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class RepositoryDaoImpl extends AbstractDao<Repository, Long> implements RepositoryDao {

    public RepositoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Repository.class);
    }

    @Override
    public List<Repository> findPage(@Nonnull PageRequestDto pageRequestDto) {
        int perPage = pageRequestDto.getPerPage();
        int offsetIndex = pageRequestDto.getPage() * perPage;
        String searchString = getContainsString(pageRequestDto.getSearchString());
        String orderString = pageRequestDto.isAscending() ? "asc" : "desc";
        return getSession()
                .createQuery("from " + Repository.class.getName() +
                        " where lower(name) like lower(:searchString) order by name "
                        + orderString, Repository.class)
                .setParameter("searchString", searchString)
                .setFirstResult(offsetIndex)
                .setMaxResults(perPage)
                .list();
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
}
