package ru.hh.school.depmonitoring.config;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.inject.Named;
import javax.sql.DataSource;

@Configuration
public class MigrationConfig {
    @Bean(initMethod = "migrate")
    protected Flyway flyway(DataSource dataSource){
        return Flyway.configure().dataSource(dataSource).load();
    }

    @Bean
    @Primary
    @DependsOn("flyway")
    protected SessionFactory sessionFactory(@Named("sessionFactoryBean") SessionFactory sessionFactory) {
        return sessionFactory;
    }
}
