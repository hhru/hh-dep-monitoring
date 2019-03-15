package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;

import java.util.Optional;

@Configuration
@Import({
        NabProdConfig.class,
        CommonConfig.class,
        NabHibernateProdConfig.class
})
public class ProdConfig {

    @Bean
    protected String oauthToken(FileSettings fileSettings) {
        return Optional.ofNullable(System.getProperty("github.oauth"))
                .orElseGet(() -> fileSettings.getString("github.oauth"));
    }

}
