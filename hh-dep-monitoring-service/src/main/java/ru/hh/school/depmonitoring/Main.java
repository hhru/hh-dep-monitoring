package ru.hh.school.depmonitoring;

import ru.hh.nab.starter.NabApplication;
import ru.hh.school.depmonitoring.config.ProdConfig;
import ru.hh.school.depmonitoring.utils.ObjectMapperContextResolver;

import static ru.hh.nab.common.properties.PropertiesUtils.setSystemPropertyIfAbsent;

public class Main {
    public static void main(String[] args) {
        setSystemPropertyIfAbsent("settingsDir", "hh-dep-monitoring-service/src/etc");
        buildApplication().run(ProdConfig.class);
    }

    static NabApplication buildApplication() {
        return NabApplication.builder()
                .configureJersey()
                .registerResources(ObjectMapperContextResolver.class)
                .bindToRoot()
                .build();
    }
}
