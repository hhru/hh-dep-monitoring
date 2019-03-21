package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class RepositoryDaoImpl extends AbstractDao<Repository, Long> implements RepositoryDao {

    @Inject
    public RepositoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Repository.class);
    }

}
