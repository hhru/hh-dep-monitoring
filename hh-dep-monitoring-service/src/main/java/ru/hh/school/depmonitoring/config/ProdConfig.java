package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;

import java.util.Optional;

@EnableScheduling
@Configuration
@Import({
        NabProdConfig.class,
        CommonConfig.class,
        NabHibernateProdConfig.class,
        GithubLoader.class
})
public class ProdConfig {
    @Bean
    protected String oauthToken(FileSettings fileSettings) {
        return getProperty(fileSettings, "github.oauth");
    }

    @Bean
    protected String githubOrganization(FileSettings fileSettings) {
        return getProperty(fileSettings, "github.organization");
    }

    @Bean
    protected String bambooLink(FileSettings fileSettings) {
        return getProperty(fileSettings, "bamboo.link");
    }

    private String getProperty(FileSettings fileSettings, String property) {
        Optional<String> oauthOptional = Optional.ofNullable(System.getProperty(property));
        if (oauthOptional.isEmpty()) {
            oauthOptional = Optional.ofNullable(fileSettings.getString(property));
        }
        return oauthOptional
                .orElseThrow(() -> new IllegalArgumentException("Error in getting " + property + " from service.properties"));
    }
}
