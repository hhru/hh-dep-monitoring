package ru.hh.school.depmonitoring.service.mapper;

import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.utils.StructCreator;

import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RelationMapperTest {
    private RelationMapper mapper;

    @Before
    public void setUp() {
        RepositoryDao repositoryDao = mock(RepositoryDao.class);
        when(repositoryDao.findOne(1L)).thenReturn(Optional.of(StructCreator.createRepositoryEntity(1L, now())));
        when(repositoryDao.findOne(2L)).thenReturn(Optional.of(StructCreator.createRepositoryEntity(2L, now())));
        mapper =  new RelationMapper(repositoryDao);
    }

    @Test
    public void toDtoWithNull() {
        assertNull(mapper.toDto((Relation) null));
    }

    @Test
    public void toDto() {
        Relation entity = StructCreator.createRelationEntity(1);
        RelationDto dto = mapper.toDto(entity);
        assertDtoEqualsEntity(entity, dto);
        assertEquals(entity.getRepositoryFrom().getName(), dto.getRepositoryFromName());
        assertEquals(entity.getRepositoryTo().getName(), dto.getRepositoryToName());
    }

    @Test
    public void toEntityWithNull() {
        assertNull(mapper.toEntity((RelationDto) null));
    }

    @Test
    public void toEntity() {
        RelationDto dto = StructCreator.createRelationDto(1);
        Relation entity = mapper.toEntity(dto);
        assertDtoEqualsEntity(entity, dto);
    }

    private void assertDtoEqualsEntity(Relation entity, RelationDto dto) {
        assertEquals(dto.getRelationId(), entity.getRelationId());
        assertEquals(dto.getRepositoryFromId(), entity.getRepositoryFrom().getRepositoryId());
        assertEquals(dto.getRepositoryToId(), entity.getRepositoryTo().getRepositoryId());
        assertEquals(dto.getPriority(), entity.getPriority());
        assertEquals(dto.getDescription(), entity.getDescription());
    }
}
