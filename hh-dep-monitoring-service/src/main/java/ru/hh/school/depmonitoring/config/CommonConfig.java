package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.hibernate.datasource.RoutingDataSource;
import ru.hh.school.depmonitoring.dao.impl.RelationDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RepositoryDaoImpl;
import ru.hh.school.depmonitoring.rs.GithubResource;
import ru.hh.school.depmonitoring.service.RepositoryService;
import ru.hh.school.depmonitoring.rs.RepositoryResource;
import ru.hh.school.depmonitoring.service.SyncService;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryLinkMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@Import({
        NabHibernateCommonConfig.class,
        MigrationConfig.class,

        RepositoryDaoImpl.class,
        RelationDaoImpl.class,

        SyncService.class,
        RepositoryService.class,

        GHRepositoryMapper.class,
        RepositoryMapper.class,
        RepositoryLinkMapper.class,

        GithubResource.class,
        RepositoryResource.class
})

public class CommonConfig {

    @Bean
    protected MappingConfig mappingConfig() {
        final MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.depmonitoring.entities");
        return mappingConfig;
    }

    @Bean
    @Primary
    protected DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return new RoutingDataSource(dataSourceFactory.create(DataSourceType.MASTER, false, fileSettings));
    }

    @Bean
    protected String oauthToken(FileSettings fileSettings) {
        return Optional.ofNullable(System.getProperty("github.oauth"))
                .orElseGet(() -> fileSettings.getString("github.oauth"));
    }

    @Bean
    protected String githubOrganization(FileSettings fileSettings) {
        return Optional.ofNullable(fileSettings.getString("github.organization"))
                .orElseThrow(() -> new IllegalArgumentException("Error in getting github.organization from service.properties"));
    }
}
