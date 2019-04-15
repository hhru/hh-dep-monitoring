package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Dependency;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class DependencyDao extends AbstractDao<Dependency, Integer> {
    public DependencyDao(SessionFactory sessionFactory) {
        super(sessionFactory, Dependency.class);
    }
}
