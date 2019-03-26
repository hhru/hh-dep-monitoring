package ru.hh.school.depmonitoring;

import org.junit.Test;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.service.RepositoryService;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class RepositoryServiceTest extends DepMonitoringTestBase {

    @Inject
    private RepositoryService repositoryService;

    @Test
    public void getAll() {
        List<RepositoryDto> result = repositoryService.getFullList();
        assertTrue(result.isEmpty());
    }

    @Test(expected = PersistenceException.class)
    public void putOneRepositoryWoID() {
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setName("name");
        repositoryService.update(repositoryDto);
    }

    @Test
    public void putOneRepositorySuccess() {
        RepositoryDto repositoryDto = StructCreator.createRepositoryDto();
        repositoryService.update(repositoryDto);
        List<RepositoryDto> result = repositoryService.getFullList();
        assertEquals(1, result.size());
    }

    @Test
    public void getRepositoryByIDNotFound() {
        assertTrue(repositoryService.getOneItem(999L).isEmpty());
    }

    @Test
    public void getRepositoryByID() {
        RepositoryDto repositoryDto = StructCreator.createRepositoryDto();
        repositoryService.update(repositoryDto);
        Optional<RepositoryDto> foundDto = repositoryService.getOneItem(1L);
        assertFalse(foundDto.isEmpty());
        testIsDtoEqual(repositoryDto, foundDto.get());
    }


    private void testIsDtoEqual(RepositoryDto dto1, RepositoryDto dto2) {
        assertEquals(dto1, dto2);

        assertNotNull(dto1);
        assertNotNull(dto2);

        assertEquals(dto1.getRepositoryId(), dto2.getRepositoryId());
        assertEquals(dto1.getHtmlUrl(), dto2.getHtmlUrl());
        assertEquals(dto1.getDescription(), dto2.getDescription());
        assertEquals(dto1.isArchived(), dto2.isArchived());
        assertEquals(dto1.isActive(), dto2.isActive());
        assertEquals(dto1.getCreatedAt(), dto2.getCreatedAt());
        assertEquals(dto1.getUpdatedAt(), dto2.getUpdatedAt());
    }


}
