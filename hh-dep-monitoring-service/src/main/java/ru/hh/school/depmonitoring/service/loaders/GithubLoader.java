package ru.hh.school.depmonitoring.service.loaders;

import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GitHub;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.exceptions.LoadExceptionType;
import ru.hh.school.depmonitoring.exceptions.LoadRuntimeException;
import ru.hh.school.depmonitoring.utils.GHConverter;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Singleton
public class GithubLoader {
    private final String oauthToken;

    public GithubLoader(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public List<GHRepositoryDto> getReposOfOrganization(String organizationName) {
        return getReposOfOrganization(getOrganizationByName(organizationName));
    }

    public List<GHRepositoryDto> getReposOfOrganization(GHOrganization ghOrganization) {
        try {
            if (ghOrganization == null || ghOrganization.getRepositories() == null || ghOrganization.getRepositories().size() == 0) {
                return List.of();
            }
            return ghOrganization.getRepositories()
                    .values()
                    .stream()
                    .map(GHConverter::convert)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new LoadRuntimeException("Error on getReposOfOrganization", e, LoadExceptionType.REPOS);
        }
    }

    private GHOrganization getOrganizationByName(String organizationName) {
        try {
            return createConnection().getOrganization(organizationName);
        } catch (IOException e) {
            throw new LoadRuntimeException("Error on getOrganizationByName", e, LoadExceptionType.ORGANIZATION_BY_NAME);
        }
    }

    private GitHub createConnection() {
        try {
            return GitHub.connectUsingOAuth(oauthToken);
        } catch (IOException e) {
            throw new LoadRuntimeException("Error on createConnection", e, LoadExceptionType.CONNECT);
        }
    }
}
