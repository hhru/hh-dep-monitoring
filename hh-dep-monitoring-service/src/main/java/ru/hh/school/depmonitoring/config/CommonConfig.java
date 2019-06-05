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
import ru.hh.school.depmonitoring.dao.impl.CoverageDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.EventDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RelationDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RepositoryDaoImpl;
import ru.hh.school.depmonitoring.dao.impl.RepositoryLinkDaoImpl;
import ru.hh.school.depmonitoring.rs.BambooResource;
import ru.hh.school.depmonitoring.rs.GithubResource;
import ru.hh.school.depmonitoring.rs.RelationResource;
import ru.hh.school.depmonitoring.rs.RepositoryLinkResource;
import ru.hh.school.depmonitoring.rs.RepositoryResource;
import ru.hh.school.depmonitoring.rs.EventResource;
import ru.hh.school.depmonitoring.rs.ArtefactResource;
import ru.hh.school.depmonitoring.service.RelationService;
import ru.hh.school.depmonitoring.service.RepositoryLinkService;
import ru.hh.school.depmonitoring.service.RepositoryService;
import ru.hh.school.depmonitoring.service.SyncService;
import ru.hh.school.depmonitoring.service.EventService;
import ru.hh.school.depmonitoring.service.ArtefactService;
import ru.hh.school.depmonitoring.service.loaders.CoverageLoader;
import ru.hh.school.depmonitoring.service.loaders.DependencyLoader;
import ru.hh.school.depmonitoring.service.loaders.coveragesourceimpl.SonarCloudCoverageSource;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;
import ru.hh.school.depmonitoring.service.mapper.RelationMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryLinkMapper;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;
import ru.hh.school.depmonitoring.service.mapper.EventMapper;
import ru.hh.school.depmonitoring.service.mapper.ArtifactMapper;
import ru.hh.school.depmonitoring.service.mapper.DependencyMapper;
import ru.hh.school.depmonitoring.service.mapper.ArtefactVersionMapper;

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
        EventDaoImpl.class,
        CoverageDaoImpl.class,

        DependencyLoader.class,
        CoverageLoader.class,
        RelationService.class,
        RepositoryService.class,
        SyncService.class,
        RepositoryService.class,
        RepositoryLinkService.class,
        EventService.class,
        SonarCloudCoverageSource.class,
        ArtefactService.class,

        GHRepositoryMapper.class,
        RelationMapper.class,
        RepositoryMapper.class,
        RepositoryLinkMapper.class,
        EventMapper.class,
        ArtifactMapper.class,
        DependencyMapper.class,
        ArtefactVersionMapper.class,

        BambooResource.class,
        GithubResource.class,
        RelationResource.class,
        RepositoryResource.class,
        RepositoryLinkResource.class,
        EventResource.class,
        ArtefactResource.class
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
