package ru.hh.school.depmonitoring;

import org.junit.Test;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.mapper.GHRepositoryMapper;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GHRepositoryMapperTest {
    private final GHRepositoryMapper mapper = new GHRepositoryMapper();

    @Test
    public void toDtoWithNull() {
        assertNull(mapper.toDto((Repository) null));
    }

    @Test
    public void toDto() {
        Repository entity = StructCreator.createRepositoryEntity(1L, LocalDateTime.parse("2008-11-04T09:16:00"));
        GHRepositoryDto dto = mapper.toDto(entity);
        assertDtoEqualsEntity(entity, dto);
    }

    @Test
    public void toEntityWithNull() {
        assertNull(mapper.toEntity((GHRepositoryDto) null));
    }

    @Test
    public void toEntity() {
        GHRepositoryDto dto = StructCreator.createGHRepositoryDto(1L);
        Repository entity = mapper.toEntity(dto);
        assertDtoEqualsEntity(entity, dto);
    }

    private void assertDtoEqualsEntity(Repository entity, GHRepositoryDto dto) {
        assertEquals(entity.getRepositoryId().longValue(), dto.getRepositoryId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getHtmlUrl(), dto.getHtmlUrl());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.isArchived(), dto.isArchived());
        assertEquals(entity.isActive(), dto.isActive());
        assertEquals(entity.getCreatedAt(), dto.getCreatedAt());
        assertEquals(entity.getUpdatedAt(), dto.getUpdatedAt());
    }
}
