package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Artifact;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class ArtifactDao extends AbstractDao<Artifact, Integer> {
    public ArtifactDao(SessionFactory sessionFactory) {
        super(sessionFactory, Artifact.class);
    }

    public Optional<Artifact> findByName(String groupName, String artifactName) {
        return getSession()
                .createQuery("from Artifact where groupName = :groupName and artifactName = :artifactName", Artifact.class)
                .setParameter("groupName", groupName)
                .setParameter("artifactName", artifactName)
                .getResultList()
                .stream()
                .findFirst();
    }
}
