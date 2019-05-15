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
import ru.hh.school.depmonitoring.dao.ArtifactDao;
import ru.hh.school.depmonitoring.dao.ArtifactVersionDao;
import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.impl.EventDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RelationDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RepositoryDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RepositoryLinkDaoImpl;
import ru.hh.school.depmonitoring.rs.GithubResource;
import ru.hh.school.depmonitoring.rs.RelationResource;
import ru.hh.school.depmonitoring.rs.RepositoryLinkResource;
import ru.hh.school.depmonitoring.rs.RepositoryResource;
import ru.hh.school.depmonitoring.rs.EventResource;
import ru.hh.school.depmonitoring.service.RelationService;
import ru.hh.school.depmonitoring.service.RepositoryLinkService;
import ru.hh.school.depmonitoring.service.RepositoryService;
import ru.hh.school.depmonitoring.service.SyncService;
import ru.hh.school.depmonitoring.service.loaders.DependencyLoader;
import ru.hh.school.depmonitoring.service.EventService;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;
import ru.hh.school.depmonitoring.service.mapper.RelationMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryLinkMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;
import ru.hh.school.depmonitoring.service.mapper.EventMapper;

import javax.sql.DataSource;

@Configuration
@Import({
        MigrationConfig.class,
        NabHibernateCommonConfig.class,

        ArtifactDao.class,
        ArtifactVersionDao.class,
        DependencyDao.class,
        RepositoryDaoImpl.class,
        RepositoryLinkDaoImpl.class,
        RelationDaoImpl.class,
        RepositoryDaoImpl.class,
        EventDaoImpl.class,

        DependencyLoader.class,
        RelationService.class,
        RepositoryService.class,
        SyncService.class,
        RepositoryService.class,
        RepositoryLinkService.class,
        EventService.class,

        GHRepositoryMapper.class,
        RelationMapper.class,
        RepositoryMapper.class,
        RepositoryLinkMapper.class,
        EventMapper.class,

        GithubResource.class,
        RelationResource.class,
        RepositoryResource.class,
        RepositoryLinkResource.class,
        EventResource.class
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
}
