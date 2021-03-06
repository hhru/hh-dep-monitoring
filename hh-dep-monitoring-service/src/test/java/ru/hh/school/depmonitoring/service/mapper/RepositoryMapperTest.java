package ru.hh.school.depmonitoring.service.mapper;

import org.junit.Test;
import org.mockito.Mockito;
import ru.hh.school.depmonitoring.dao.CoverageDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class RepositoryMapperTest {

    private final RepositoryMapper repositoryMapper = new RepositoryMapper(
            Mockito.mock(RepositoryLinkMapper.class),
            Mockito.mock(EventDao.class),
            Mockito.mock(ArtifactMapper.class),
            Mockito.mock(CoverageDao.class)
    );

    @Test
    public void toEntityNull() {
        Repository entity = repositoryMapper.toEntity((RepositoryDto) null);
        assertNull(entity);
    }

    @Test
    public void toEntity() {
        RepositoryDto repositoryDto = StructCreator.createRepositoryDto();
        Repository entity = repositoryMapper.toEntity(repositoryDto);
        testEqual(entity, repositoryDto);
    }

    @Test
    public void toDtoNull() {
        RepositoryDto dto = repositoryMapper.toDto((Repository) null);
        assertNull(dto);
    }

    @Test
    public void toDto() {
        Repository entity = StructCreator.createRepositoryEntity(1L, LocalDateTime.now());
        RepositoryDto dto = repositoryMapper.toDto(entity);

        testEqual(entity, dto);
        assertFalse(dto.getHasRelatedTo());
        assertFalse(dto.getHasRelatedFrom());
    }

    private void testEqual(Repository entity, RepositoryDto dto) {
        assertEquals(entity.getRepositoryId(), dto.getRepositoryId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getHtmlUrl(), dto.getHtmlUrl());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.isArchived(), dto.isArchived());
        assertEquals(entity.isActive(), dto.isActive());
        assertEquals(entity.getCreatedAt(), dto.getCreatedAt());
        assertEquals(entity.getUpdatedAt(), dto.getUpdatedAt());
    }


}
