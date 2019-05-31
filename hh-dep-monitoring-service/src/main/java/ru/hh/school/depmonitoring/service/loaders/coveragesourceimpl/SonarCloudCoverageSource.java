package ru.hh.school.depmonitoring.service.loaders.coveragesourceimpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;
import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.coverage.SonarCloudCoverageDto;
import ru.hh.school.depmonitoring.dto.coverage.SonarCloudCoverageDto.Measure;
import ru.hh.school.depmonitoring.entities.Coverage;
import ru.hh.school.depmonitoring.entities.CoverageSourceType;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.exceptions.LoadException;
import ru.hh.school.depmonitoring.service.loaders.CoverageSource;
import ru.hh.school.depmonitoring.service.loaders.DependencyLoader;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@Named
@Singleton
public class SonarCloudCoverageSource implements CoverageSource {


    private final static Logger log = LoggerFactory.getLogger(DependencyLoader.class);

    private final RepositoryDao repositoryDao;
    private final CoverageDao coverageDao;
    private final String sonarCloudLink;

    public SonarCloudCoverageSource(RepositoryDao repositoryDao, CoverageDao coverageDao, String sonarCloudLink) {
        this.repositoryDao = repositoryDao;
        this.coverageDao = coverageDao;
        this.sonarCloudLink = sonarCloudLink;
    }

    public float getCoverage(String key) throws LoadException, IOException {
        SonarCloudCoverageDto dto = loadDto(key);
        Measure[] measures = dto.getMeasures();
        for (Measure measure : measures) {
            if ("coverage".equals(measure.getMetric())) {
                return measure.getValue();
            }
        }
        throw new LoadException("Can not load coverage for repository " + key);
    }

    private SonarCloudCoverageDto loadDto(String key) throws IOException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target(sonarCloudLink)
                .queryParam("component", key)
                .queryParam("metricKeys", "coverage")
                .request()
                .get();
        String json = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return mapper.readValue(json, SonarCloudCoverageDto.class);
    }

    public void saveCoverageFromSonarCloud(Repository repository, RepositoryLink link) {
        String key = UriComponentsBuilder.fromUriString(link.getLinkUrl())
                .build()
                .getQueryParams()
                .get("id")
                .get(0);
        float newCoverage;
        try {
            newCoverage = getCoverage(key);
        } catch (LoadException | IOException e) {
            log.error(e.getMessage());
            return;
        }
        Optional<Coverage> coverageOptional = coverageDao.findLastForRepositoryByType(repository.getRepositoryId(), CoverageSourceType.SONAR_CLOUD);
        if (!coverageOptional.isPresent() || coverageOptional.get().getCoverage() != newCoverage) {
            Coverage coverage = new Coverage(LocalDateTime.now(), CoverageSourceType.SONAR_CLOUD, repository.getRepositoryId(), newCoverage);
            coverageDao.create(coverage);
        }
    }

}
