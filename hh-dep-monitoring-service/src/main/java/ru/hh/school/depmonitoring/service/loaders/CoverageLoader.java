package ru.hh.school.depmonitoring.service.loaders;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dao.RepositoryLinkDao;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.service.loaders.coveragesourceimpl.SonarCloudCoverageSource;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named
@Singleton
public class CoverageLoader {
    private final RepositoryDao repositoryDao;
    private final CoverageDao coverageDao;
    private final RepositoryLinkDao repositoryLinkDao;
    private final SonarCloudCoverageSource sonarCloudCoverageSource;


    private static final Logger log = LoggerFactory.getLogger(DependencyLoader.class);

    public CoverageLoader(RepositoryDao repositoryDao,
                          CoverageDao coverageDao,
                          RepositoryLinkDao repositoryLinkDao,
                          SonarCloudCoverageSource sonarCloudCoverageSource) {
        this.repositoryDao = repositoryDao;
        this.coverageDao = coverageDao;
        this.repositoryLinkDao = repositoryLinkDao;
        this.sonarCloudCoverageSource = sonarCloudCoverageSource;
    }

    @Transactional
    public void loadCoverage() {
        for (Repository repository : repositoryDao.findAll()) {
            long repositoryId = repository.getRepositoryId();
            List<RepositoryLink> links = repositoryLinkDao.findForRepository(repositoryId);
            for (RepositoryLink link : links) {
                loadCoverageFromSource(repository, link);
            }
        }
    }

    public void loadCoverageFromSource(Repository repository, RepositoryLink link) {
        switch (link.getLinkType()) {
            case SONARCLOUD:
                sonarCloudCoverageSource.saveCoverageFromSonarCloud(repository, link);
            default:
                return;
        }
    }



}
