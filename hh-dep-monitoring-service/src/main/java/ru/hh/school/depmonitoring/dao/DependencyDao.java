package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class DependencyDao extends AbstractDao<Dependency, Integer> {
    public DependencyDao(SessionFactory sessionFactory) {
        super(sessionFactory, Dependency.class);
    }

    public Optional<Dependency> findByRepositoryAndArtifact(Repository repository, Artifact artifact) {
        return getSession()
                .createQuery("from Dependency where repository = :repository and artifactVersion.artifact = :artifact", Dependency.class)
                .setParameter("repository", repository)
                .setParameter("artifact", artifact)
                .getResultList()
                .stream()
                .findFirst();
    }
}
