package ru.hh.school.depmonitoring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.depmonitoring.loaders.GithubLoader;
import ru.hh.school.depmonitoring.rs.Resource;

@Configuration
@Import({
        Resource.class,
        GithubLoader.class
})
public class CommonConfig {
}
