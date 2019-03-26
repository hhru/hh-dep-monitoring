package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class RepositoryDaoImpl extends AbstractDao<Repository, Long> implements RepositoryDao {

    public RepositoryDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Repository.class);
    }

}
