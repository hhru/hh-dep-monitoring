package ru.hh.school.depmonitoring.service;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dto.RepositoryDto;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RepositoryServiceTest extends DepMonitoringTestBase {

    @Inject
    private RepositoryService repositoryService;

    @Test
    public void getAll() {
        List<RepositoryDto> result = repositoryService.getFullList();
        assertTrue(result.isEmpty());
    }

    @Test
    public void getRepositoryByIDNotFound() {
        assertTrue(repositoryService.getOneItem(999L).isEmpty());
    }

}
