package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.entities.Coverage;
import ru.hh.school.depmonitoring.entities.CoverageSourceType;

import java.util.Optional;

public class CoverageDaoImpl extends AbstractDao<Coverage, Integer> implements CoverageDao {
    public CoverageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Coverage.class);
    }

    @Override
    public Optional<Coverage> findLastForRepositoryByType(long repositoryId, CoverageSourceType type) {
        return getSession()
                .createQuery("from Coverage where repositoryId = :repositoryId and " +
                        "sourceType = :sourceType order by analizeTime desc", Coverage.class)
                .setParameter("repositoryId", repositoryId)
                .setParameter("sourceType", type)
                .setMaxResults(1)
                .uniqueResultOptional();
    }
}
