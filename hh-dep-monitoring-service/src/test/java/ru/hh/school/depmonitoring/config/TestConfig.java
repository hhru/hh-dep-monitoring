package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Import({
        CommonConfig.class,
        NabTestConfig.class,
        NabHibernateTestBaseConfig.class
})
public class TestConfig {
    @Bean
    protected GithubLoader githubLoader(String githubOrganization) {
        GithubLoader githubLoader = mock(GithubLoader.class);
        List<GHRepositoryDto> repositoryDtoList = StructCreator.createGHRepositoryDtoList(3L);
        when(githubLoader.getReposOfOrganization(githubOrganization)).thenReturn(repositoryDtoList);
        return githubLoader;
    }
}
