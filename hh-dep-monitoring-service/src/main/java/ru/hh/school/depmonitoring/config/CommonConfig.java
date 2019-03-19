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
import ru.hh.school.depmonitoring.loaders.GithubLoader;
import ru.hh.school.depmonitoring.rs.Resource;

import javax.sql.DataSource;

@Configuration
@Import({
        NabHibernateCommonConfig.class,
        MigrationConfig.class,

        RepositoryDaoImpl.class,
        RelationDaoImpl.class,

        GithubLoader.class,

        Resource.class
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
