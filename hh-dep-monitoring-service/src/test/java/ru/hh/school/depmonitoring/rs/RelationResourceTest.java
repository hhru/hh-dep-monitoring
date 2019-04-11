package ru.hh.school.depmonitoring.rs;

import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class RelationResourceTest extends DepMonitoringTestBase {
    @Inject
    private DBUtils dbUtils;

    @Before
    public void setUp() {
        dbUtils.fillRepositoryTable();
    }

    @Test
    public void getRelationsDependOnNotExistingRepository() {
        Response response = target("/relations/depend-on/100500")
                .request()
                .get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void getRelationsDependOn() {
        Relation relation = dbUtils.addRelation();
        Map<RepositoryRelationPriority, List<RelationDto>> relationDtoMap;
        relationDtoMap = target("/relations/depend-on/" + relation.getRepositoryFrom().getRepositoryId())
                .request()
                .get()
                .readEntity(new GenericType<Map<RepositoryRelationPriority, List<RelationDto>>>() {
                });
        assertEqualsRelationWithMap(relation, relationDtoMap);
    }

    @Test
    public void getRelationsDependOutNotExistingRepository() {
        Response response = target("/relations/depend-out/100500")
                .request()
                .get();
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void getRelationsDependOut() {
        Relation relation = dbUtils.addRelation();
        Map<RepositoryRelationPriority, List<RelationDto>> relationDtoMap;
        relationDtoMap = target("/relations/depend-out/" + relation.getRepositoryTo().getRepositoryId())
                .request()
                .get()
                .readEntity(new GenericType<Map<RepositoryRelationPriority, List<RelationDto>>>() {
                });
        assertEqualsRelationWithMap(relation, relationDtoMap);
    }

    @Test
    public void insertRelationWithoutDto() {
        Response response = target("/relations")
                .request()
                .acceptEncoding("identity")
                .post(null);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void insertRelation() {
        RelationDto dto = StructCreator.createRelationDto(null);
        Response response = target("/relations")
                .request()
                .acceptEncoding("identity")
                .post(Entity.json(dto));
        isDtoSaved(response);
    }

    @Test
    public void updateRelationWithNullData() {
        Relation relation = dbUtils.addRelation();
        RelationDto dto = RelationDto.builder()
                .withRepositoryFromId(null)
                .withRepositoryToId(null)
                .withPriority(null)
                .withDescription(null)
                .build();
        Response response = target("/relations/" + relation.getRelationId())
                .request()
                .acceptEncoding("identity")
                .put(Entity.json(dto));
        isDtoSaved(response);
    }

    @Test
    public void updateRelationWithoutNewData() {
        Relation relation = dbUtils.addRelation();
        RelationDto dto = RelationDto.builder()
                .withRepositoryFromId(relation.getRepositoryFrom().getRepositoryId())
                .withRepositoryToId(relation.getRepositoryTo().getRepositoryId())
                .withPriority(relation.getPriority())
                .withDescription(relation.getDescription())
                .build();
        Response response = target("/relations/" + relation.getRelationId())
                .request()
                .acceptEncoding("identity")
                .put(Entity.json(dto));
        isDtoSaved(response);
    }

    @Test
    public void updateRelation() {
        Relation relation = dbUtils.addRelation();
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
        isDtoSaved(response);
    }

    @Test
    public void deleteRelation() {
        Relation relation = dbUtils.addRelation();
        Response response = target("/relations/" + relation.getRelationId())
                .request()
                .acceptEncoding("identity")
                .delete();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    private void assertEqualsRelationWithMap(Relation relation, Map<RepositoryRelationPriority, List<RelationDto>> relationDtoMap) {
        assertNotNull(relationDtoMap);
        assertEquals(1, relationDtoMap.size());
        List<RelationDto> relationDtoList = relationDtoMap.get(relation.getPriority());
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

    private void isDtoSaved(Response response) {
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.hasEntity());
        RelationDto responseDto = response.readEntity(RelationDto.class);
        assertNotNull(responseDto);
        assertNotNull(responseDto.getRelationId());
    }
}
