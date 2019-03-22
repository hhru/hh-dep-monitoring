package ru.hh.school.depmonitoring.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
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
    private final GHRepositoryMapper mapper;
    private final RepositoryDao repositoryDao;

    public SyncService(String githubOrganization, GithubLoader githubLoader, GHRepositoryMapper mapper, RepositoryDao repositoryDao) {
        this.githubOrganization = githubOrganization;
        this.githubLoader = githubLoader;
        this.mapper = mapper;
        this.repositoryDao = repositoryDao;
    }

    @Scheduled(initialDelay = 60*1000L, fixedDelay = 15*60*1000L)
    @Transactional
    public void syncGithub() {
        List<GHRepositoryDto> repositoryList= githubLoader.getReposOfOrganization(githubOrganization);
        for (GHRepositoryDto repositoryDto : repositoryList) {
            var repositoryOptional = repositoryDao.findOne(repositoryDto.getRepositoryId());
            if (repositoryOptional.isPresent()) {
                if (!Objects.equals(repositoryDto.getUpdatedAt(), repositoryOptional.get().getUpdatedAt())) {
                    repositoryDao.update(mapper.toEntity(repositoryDto));
                }
            } else {
                repositoryDao.create(mapper.toEntity(repositoryDto));
            }
        }
    }
}
