package ru.hh.school.depmonitoring.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.ArtifactVersion;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Optional;

@Named
@Singleton
public class ArtifactVersionDao extends AbstractDao<ArtifactVersion, Integer> {
    public ArtifactVersionDao(SessionFactory sessionFactory) {
        super(sessionFactory, ArtifactVersion.class);
    }

    public Optional<ArtifactVersion> findByArtifactAndVersion(Artifact artifact, String version) {
        return getSession()
                .createQuery("from ArtifactVersion where artifact = :artifact and version = :version", ArtifactVersion.class)
                .setParameter("artifact", artifact)
                .setParameter("version", version)
                .getResultList()
                .stream()
                .findFirst();
    }
}
