package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.Dependency;
import ru.hh.school.depmonitoring.entities.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class DependencyDao extends AbstractDao<Dependency, Integer> {
    public DependencyDao(SessionFactory sessionFactory) {
        super(sessionFactory, Dependency.class);
    }

    public List<Dependency> findByRepositoryAndArtifact(Repository repository, Artifact artifact) {
        return getSession()
                .createQuery("from Dependency where repository = :repository and artifactVersion.artifact = :artifact", Dependency.class)
                .setParameter("repository", repository)
                .setParameter("artifact", artifact)
                .getResultList();
    }

    public List<Dependency> findByParentDependency(Dependency dependency) {
        return getSession()
                .createQuery("from Dependency where parentDependency = :parentDependency", Dependency.class)
                .setParameter("parentDependency", dependency)
                .getResultList();
    }

    public List<Dependency> findByArtifact(Artifact artifact) {
        return getSession()
                .createQuery("from Dependency where artifactVersion.artifact = :artifact", Dependency.class)
                .setParameter("artifact", artifact)
                .getResultList();
    }

    public List<Dependency> findRepositoryFirstLevel(Repository repository) {
        return getSession()
                .createQuery("from Dependency where repository = :repository and parentDependency is null", Dependency.class)
                .setParameter("repository", repository)
                .getResultList();
    }
}
