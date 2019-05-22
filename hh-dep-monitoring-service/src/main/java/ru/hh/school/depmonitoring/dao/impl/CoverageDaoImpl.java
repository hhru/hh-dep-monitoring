package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.entities.Coverage;

public class CoverageDaoImpl extends AbstractDao<Coverage, Integer> implements CoverageDao {
    public CoverageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Coverage.class);
    }
}
