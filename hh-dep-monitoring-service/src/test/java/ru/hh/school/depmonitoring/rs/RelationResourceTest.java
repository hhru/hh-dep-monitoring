package ru.hh.school.depmonitoring.rs;

import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.RelationDao;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.service.mapper.RelationMapper;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class RelationResourceTest extends DepMonitoringTestBase {
    @Inject
    private DBUtils dbUtils;

    @Inject
    private RelationDao relationDao;

    @Inject
    private RelationMapper relationMapper;

    @Before
    public void setUp() {
        dbUtils.fillRepositoryTable();
    }

    @Test
    public void getRelationsDependOn() {
        Relation relation = dbUtils.doInTransaction(this::addRelation);
        List<RelationDto> relationDtoList = target("/relations/dependOn/" + relation.getRepositoryFrom().getRepositoryId())
                .request()
                .get()
                .readEntity(new GenericType<List<RelationDto>>() {
                });
    }

    @Test
    public void getRelationsDependOut() {
        Relation relation = dbUtils.doInTransaction(this::addRelation);
        List<RelationDto> relationDtoList = target("/relations/dependOut/" + relation.getRepositoryTo().getRepositoryId())
                .request()
                .get()
                .readEntity(new GenericType<List<RelationDto>>() {
                });
        assertEqualsRelationWithList(relation, relationDtoList);
    }

    @Test
    public void insertRelation() {
        RelationDto dto = StructCreator.createRelationDto(null);
        Response response = target("/relations")
                .request()
                .acceptEncoding("identity")
                .post(Entity.json(dto));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void updateRelation() {
        Relation relation = dbUtils.doInTransaction(this::addRelation);
        RelationDto dto = RelationDto.builder()
                .withRepositoryFromId(relation.getRepositoryTo().getRepositoryId())
                .withRepositoryToId(relation.getRepositoryFrom().getRepositoryId())
                .withPriority(RepositoryRelationPriority.OPTIONAL)
                .withDescription("New description")
                .build();
        Response response = target("/relations/" + relation.getRelationId())
                .request()
                .acceptEncoding("identity")
                .put(Entity.json(dto));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void deleteRelation() {
        Relation relation = dbUtils.doInTransaction(this::addRelation);
        Response response = target("/relations/" + relation.getRelationId())
                .request()
                .acceptEncoding("identity")
                .delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    private void assertEqualsRelationWithList(Relation relation, List<RelationDto> relationDtoList) {
        assertNotNull(relationDtoList);
        assertEquals(1, relationDtoList.size());
        assertEquals(relation.getRelationId(), relationDtoList.get(0).getRelationId());
        assertEquals(relation.getRepositoryFrom().getRepositoryId(), relationDtoList.get(0).getRepositoryFromId());
        assertEquals(relation.getRepositoryFrom().getName(), relationDtoList.get(0).getRepositoryFromName());
        assertEquals(relation.getRepositoryTo().getRepositoryId(), relationDtoList.get(0).getRepositoryToId());
        assertEquals(relation.getRepositoryTo().getName(), relationDtoList.get(0).getRepositoryToName());
        assertEquals(relation.getPriority(), relationDtoList.get(0).getPriority());
        assertEquals(relation.getDescription(), relationDtoList.get(0).getDescription());
    }

    private Relation addRelation() {
        RelationDto relationDto = StructCreator.createRelationDto(null);
        Relation result = relationMapper.toEntity(relationDto);
        relationDao.create(result);
        return result;
    }
}
