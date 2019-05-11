package ru.hh.school.depmonitoring.service.mapper;

import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.ArtifactDto;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArtifactMapperTest extends DepMonitoringTestBase {
    @Inject
    private ArtifactMapper mapper;

    @Before
    public void setUp() {
        RepositoryDao repositoryDao = mock(RepositoryDao.class);
        when(repositoryDao.findOne(1L)).thenReturn(Optional.of(StructCreator.createRepositoryEntity(1L, LocalDateTime.now())));
        mapper =  new ArtifactMapper(repositoryDao);
    }

    @Test
    public void toDto() {
        Artifact entity = new Artifact();
        entity.setArtifactId(1);
        entity.setGroupName("Group");
        entity.setArtifactName("Artifact");

        ArtifactDto dto = mapper.toDto(entity);
        assertArtifactEquals(entity, dto);
    }

    @Test
    public void toEntity() {
        ArtifactDto dto = ArtifactDto.builder()
                .withArtifactId(1)
                .withGroupName("Group")
                .withArtifactName("Artifact")
                .build();

        Artifact entity = mapper.toEntity(dto);
        assertArtifactEquals(entity, dto);
    }

    @Test
    public void toDtoWithRepository() {
        Artifact entity = new Artifact();
        entity.setArtifactId(1);
        entity.setGroupName("Group");
        entity.setArtifactName("Artifact");
        entity.setRepository(StructCreator.createRepositoryEntity(1L, LocalDateTime.now()));

        ArtifactDto dto = mapper.toDto(entity);
        assertArtifactEquals(entity, dto);
    }

    @Test
    public void toEntityWithRepository() {
        ArtifactDto dto = ArtifactDto.builder()
                .withArtifactId(1)
                .withGroupName("Group")
                .withArtifactName("Artifact")
                .withRepositoryId(1L)
                .build();

        Artifact entity = mapper.toEntity(dto);
        assertArtifactEquals(entity, dto);
    }

    private void assertArtifactEquals(Artifact entity, ArtifactDto dto) {
        assertEquals(entity.getArtifactId(), dto.getArtifactId());
        assertEquals(entity.getGroupName(), dto.getGroupName());
        assertEquals(entity.getArtifactName(), dto.getArtifactName());
        if (entity.getRepository() == null) {
            assertNull(dto.getRepositoryId());
        }
        else {
            assertEquals(entity.getRepository().getRepositoryId(), dto.getRepositoryId());
        }
    }
}
