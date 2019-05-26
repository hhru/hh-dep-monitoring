package ru.hh.school.depmonitoring.service;

import org.junit.Test;
import org.mockito.Mockito;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SyncServiceTest {
    private final String githubOrganization = "hhru";

    @Test
    public void service() {
        GithubLoader githubLoader = mock(GithubLoader.class);
        List<GHRepositoryDto> repositoryDtoList = StructCreator.createGHRepositoryDtoList(3L);
        when(githubLoader.getReposOfOrganization(githubOrganization)).thenReturn(repositoryDtoList);

        GHRepositoryMapper mapper = mock(GHRepositoryMapper.class);

        RepositoryDao repositoryDao = mock(RepositoryDao.class);
        when(repositoryDao.findOne(1L))
                .thenReturn(Optional.ofNullable(StructCreator.createRepositoryEntity(1L, repositoryDtoList.get(0).getUpdatedAt())));
        when(repositoryDao.findOne(2L))
                .thenReturn(Optional.ofNullable(StructCreator.createRepositoryEntity(2L, repositoryDtoList.get(1).getUpdatedAt().plusDays(1L))));
        when(repositoryDao.findOne(3L)).thenReturn(Optional.empty());

        SyncService syncService = new SyncService(githubOrganization, githubLoader, mapper, repositoryDao, null, null);
        syncService.syncGithub();

        Mockito.verify(githubLoader, Mockito.times(1)).getReposOfOrganization(githubOrganization);
        Mockito.verify(mapper, Mockito.times(1)).toEntity(repositoryDtoList.get(1));
        Mockito.verify(mapper, Mockito.times(1)).toEntity(repositoryDtoList.get(2));
        Mockito.verify(repositoryDao, Mockito.times(1)).findOne(1L);
        Mockito.verify(repositoryDao, Mockito.times(1)).findOne(2L);
        Mockito.verify(repositoryDao, Mockito.times(1)).findOne(3L);
    }
}
