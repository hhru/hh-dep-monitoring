package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class RelationDaoImpl extends AbstractDao<Relation, Integer> implements RelationDao {

    public RelationDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Relation.class);
    }

    @Override
    public List<Relation> findRelationsByMainRepositoryId(Repository repository) {
        return getSession()
                .createQuery("from Relation where repositoryFrom = :repository order by repositoryTo.name asc", Relation.class)
                .setParameter("repository", repository)
                .getResultList();
    }

    @Override
    public List<Relation> findRelationsByDependentRepositoryId(Repository repository) {
        return getSession()
                .createQuery("from Relation where repositoryTo = :repository order by repositoryFrom.name asc", Relation.class)
                .setParameter("repository", repository)
                .getResultList();
    }
}
