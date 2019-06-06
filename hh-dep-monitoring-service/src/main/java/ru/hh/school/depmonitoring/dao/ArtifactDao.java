package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Artifact;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
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

    public List<String> getNames() {
        return getSession()
                .createQuery("select artifactName from Artifact", String.class)
                .getResultList();
    }

    public List<String> getNamesContains(String searchKeyword) {
        return getSession()
                .createQuery("select artifactName from Artifact where upper(artifactName) like :searchKeyword", String.class)
                .setParameter("searchKeyword", "%" + searchKeyword.toUpperCase() + "%")
                .getResultList();
    }
}
