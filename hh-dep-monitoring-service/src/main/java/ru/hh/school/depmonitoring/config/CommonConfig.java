package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.starter.AppMetadata;
import ru.hh.school.depmonitoring.rs.Resource;

import javax.inject.Inject;

@Configuration
@Import({
})
public class CommonConfig {

  private final FileSettings fileSettings;
  private final String serviceName;
  private final AppMetadata appMetadata;

  @Inject
  public CommonConfig(FileSettings fileSettings, String serviceName, AppMetadata appMetadata) {
    this.fileSettings = fileSettings;
    this.serviceName = serviceName;
    this.appMetadata = appMetadata;
  }

  @Bean
  Resource resource() {
    return new Resource();
  }
}
