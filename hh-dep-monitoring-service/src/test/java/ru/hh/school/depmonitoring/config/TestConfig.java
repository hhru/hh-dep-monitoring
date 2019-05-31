package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.testbase.JettyTestContainer;
import ru.hh.nab.testbase.NabTestConfig;
import ru.hh.nab.testbase.hibernate.NabHibernateTestBaseConfig;
import ru.hh.nab.testbase.spring.NabTestContext;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Import({
        CommonConfig.class,
        NabTestConfig.class,
        NabHibernateTestBaseConfig.class,
        DBUtils.class
})
public class TestConfig {
    @Bean
    protected String oauthToken(FileSettings fileSettings) {
        return "TEST_TOKEN";
    }

    @Bean
    protected String githubOrganization(FileSettings fileSettings) {
        return "hhru";
    }

    @Bean
    protected GithubLoader githubLoader(String githubOrganization) {
        GithubLoader githubLoader = mock(GithubLoader.class);
        List<GHRepositoryDto> repositoryDtoList = StructCreator.createGHRepositoryDtoList(3L);
        when(githubLoader.getReposOfOrganization(githubOrganization)).thenReturn(repositoryDtoList);
        return githubLoader;
    }

    @Bean
    protected String bambooLink(NabTestContext testContext) {
        return JettyTestContainer.getServerAddress(testContext.getPortHolder().getPort()).toString().concat("/bamboo");
    }

    @Bean
    protected String sonarCloudLink(NabTestContext testContext) {
        return JettyTestContainer.getServerAddress(testContext.getPortHolder().getPort()).toString().concat("/sonarCloud");
    }
}
