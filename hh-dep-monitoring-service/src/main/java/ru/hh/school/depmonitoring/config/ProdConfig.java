package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;

@EnableScheduling
@Configuration
@Import({
        NabProdConfig.class,
        CommonConfig.class,
        NabHibernateProdConfig.class,
        GithubLoader.class
})
public class ProdConfig {
}
