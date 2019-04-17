package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class ArtifactVersionDao extends AbstractDao<ArtifactVersion, Integer> {
    public ArtifactVersionDao(SessionFactory sessionFactory) {
        super(sessionFactory, ArtifactVersion.class);
    }
}
