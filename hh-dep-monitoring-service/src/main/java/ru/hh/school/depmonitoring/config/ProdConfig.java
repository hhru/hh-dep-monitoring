package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.starter.NabProdConfig;

@Configuration
@Import({
    NabProdConfig.class,

    CommonConfig.class
})
public class ProdConfig {
}
