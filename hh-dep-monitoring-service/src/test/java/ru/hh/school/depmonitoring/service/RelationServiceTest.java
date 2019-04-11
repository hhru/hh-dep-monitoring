package ru.hh.school.depmonitoring.service;

import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RelationServiceTest extends DepMonitoringTestBase {

    @Inject
    private DBUtils dbUtils;

    @Inject
    private RelationDao relationDao;

    @Inject
    private RelationService relationService;

    @Before
    public void setUp() {
        dbUtils.fillRepositoryTable();
    }

    @Test
    public void getRelationsDependOn() {
        assertTrue(relationService.getRelationsDependOn(1L).isEmpty());
    }

    @Test
    public void getRelationsDependOut() {
        assertTrue(relationService.getRelationsDependOut(1L).isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void insertEmptyRelation() {
        relationService.insertRelation(RelationDto.builder().withPriority(RepositoryRelationPriority.CRITICAL).build());
    }

    @Test
    public void insertRelation() {
        RelationDto relationDto = relationService.insertRelation(StructCreator.createRelationDto(null));
        assertNotNull(relationDto.getRelationId());
        List<Relation> relationList = dbUtils.doInTransaction(relationDao::findAll);
        assertNotNull(relationList);
        assertEquals(1, relationList.size());
        assertEquals(relationDto.getRelationId(), (relationList.get(0)).getRelationId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateRelationWithNew() {
        RelationDto relationDto = StructCreator.createRelationDto(null);
        relationService.updateRelation(1, relationDto);
    }

    @Test
    public void updateRelation() {
        Relation relation = dbUtils.addRelation();
        RelationDto relationDto = RelationDto.builder()
                .withRepositoryFromId(relation.getRepositoryTo().getRepositoryId())
                .withRepositoryToId(relation.getRepositoryFrom().getRepositoryId())
                .withPriority(RepositoryRelationPriority.PARTIAL)
                .withDescription("New description")
                .build();
        RelationDto newRelationDto = relationService.updateRelation(relation.getRelationId(), relationDto);
        assertNotNull(newRelationDto);
        assertNotNull(newRelationDto.getRelationId());
        assertEquals(relationDto.getRepositoryFromId(), newRelationDto.getRepositoryFromId());
        assertEquals(relationDto.getRepositoryToId(), newRelationDto.getRepositoryToId());
        assertEquals(relationDto.getPriority(), newRelationDto.getPriority());
        assertEquals(relationDto.getDescription(), newRelationDto.getDescription());
    }

    @Test
    public void deleteRelation() {
        Relation relation = dbUtils.addRelation();
        relationService.deleteRelation(relation.getRelationId());
        assertTrue(dbUtils.doInTransaction(relationDao::findAll).isEmpty());
    }
}
