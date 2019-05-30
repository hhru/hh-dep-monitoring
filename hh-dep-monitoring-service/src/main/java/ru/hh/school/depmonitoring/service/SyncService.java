package ru.hh.school.depmonitoring.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.loaders.CoverageLoader;
import ru.hh.school.depmonitoring.service.loaders.DependencyLoader;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Named
@Singleton
public class SyncService {
    private final String githubOrganization;
    private final GithubLoader githubLoader;
    private final GHRepositoryMapper repositoryMapper;
    private final RepositoryDao repositoryDao;
    private final DependencyLoader dependencyLoader;
    private final CoverageLoader coverageLoader;

    public SyncService(
            String githubOrganization,
            GithubLoader githubLoader,
            GHRepositoryMapper repositoryMapper,
            RepositoryDao repositoryDao,
            DependencyLoader dependencyLoader,
            CoverageLoader coverageLoader) {
        this.githubOrganization = githubOrganization;
        this.githubLoader = githubLoader;
        this.repositoryMapper = repositoryMapper;
        this.repositoryDao = repositoryDao;
        this.dependencyLoader = dependencyLoader;
        this.coverageLoader = coverageLoader;
    }

    @Scheduled(initialDelay = 60 * 1000L, fixedDelay = 15 * 60 * 1000L)
    @Transactional
    public void syncGithub() {
        List<GHRepositoryDto> repositoryList = githubLoader.getReposOfOrganization(githubOrganization);
        for (GHRepositoryDto repositoryDto : repositoryList) {
            var repositoryOptional = repositoryDao.findOne(repositoryDto.getRepositoryId());
            Repository repository = repositoryMapper.toEntity(repositoryDto);
            if (repositoryOptional.isPresent()) {
                if (!Objects.equals(repositoryDto.getUpdatedAt(), repositoryOptional.get().getUpdatedAt())) {
                    repositoryDao.update(repository);
                }
            } else {
                repositoryDao.create(repository);
            }
        }
    }

    @Scheduled(initialDelay = 2 * 60 * 1000L, fixedDelay = 15 * 60 * 1000L)
    public void syncBamboo() {
        dependencyLoader.saveDependencyData();
    }

    public void syncBamboo(String bambooLink) {
        dependencyLoader.saveDependencyData(bambooLink);
    }


    @Scheduled(initialDelay = 2 * 60 * 1000L, fixedDelay = 15 * 60 * 1000L)
    public void syncCoverage() {
        coverageLoader.loadCoverage();
    }
}
