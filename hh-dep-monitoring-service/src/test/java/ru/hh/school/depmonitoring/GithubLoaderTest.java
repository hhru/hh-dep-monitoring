package ru.hh.school.depmonitoring;

import org.junit.Test;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHRepository;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.service.loaders.GithubLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GithubLoaderTest {
    private final String token = "TEST_TOKEN";
    private GithubLoader githubLoader = new GithubLoader(token);

    @Test
    public void getRepositoriesWithNull() {
        List<GHRepositoryDto> repositoryList = githubLoader.getReposOfOrganization((GHOrganization) null);
        assertTrue(repositoryList.isEmpty());
    }

    @Test
    public void getRepositoriesWithEmptyOrganization() throws IOException {
        GHOrganization ghOrganization = mock(GHOrganization.class);
        when(ghOrganization.getRepositories()).thenReturn(null);
        List<GHRepositoryDto> repositoryList = githubLoader.getReposOfOrganization(ghOrganization);
        assertTrue(repositoryList.isEmpty());
    }

    @Test
    public void getRepositories() throws IOException {
        Map<String, GHRepository> ghRepositoryMap = LongStream.range(1, 11)
                .mapToObj(RepositoryMockStub::new)
                .collect(Collectors.toMap(GHRepository::getName, Function.identity()));
        GHOrganization ghOrganization = mock(GHOrganization.class);
        when(ghOrganization.getRepositories()).thenReturn(ghRepositoryMap);
        List<GHRepositoryDto> repositoryList = githubLoader.getReposOfOrganization(ghOrganization);
        assertEquals(ghRepositoryMap.size(), repositoryList.size());
    }

    class RepositoryMockStub extends GHRepository {
        private final long id;

        RepositoryMockStub(long id) {
            this.id = id;
        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public String getName() {
            return "Repo " + id;
        }

        @Override
        public URL getHtmlUrl() {
            try {
                return new URL("http://github.com/test");
            } catch (MalformedURLException e) {
                return null;
            }
        }

        @Override
        public String getDescription() {
            return "Test description";
        }

        @Override
        public boolean isArchived() {
            return false;
        }

        @Override
        public Date getCreatedAt() {
            return new Date(0);
        }

        @Override
        public Date getUpdatedAt() {
            return new Date(100);
        }
    }
}
