package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.entities.Relation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class RelationDaoImpl extends AbstractDao<Relation, Integer> implements RelationDao {

    @Inject
    public RelationDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Relation.class);
    }

}
