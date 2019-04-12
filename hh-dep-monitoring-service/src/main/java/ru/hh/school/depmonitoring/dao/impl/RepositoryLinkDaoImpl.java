package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RepositoryLinkDao;
import ru.hh.school.depmonitoring.entities.RepositoryLink;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class RepositoryLinkDaoImpl extends AbstractDao<RepositoryLink, Integer> implements RepositoryLinkDao {
    public RepositoryLinkDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, RepositoryLink.class);
    }

    @Override
    public List<RepositoryLink> findForRepository(long repositoryId) {
        return getSession()
                .createQuery("from RepositoryLink where repositoryId = :repositoryId order by linkType", RepositoryLink.class)
                .setParameter("repositoryId", repositoryId)
                .getResultList();
    }
}
