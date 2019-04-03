package ru.hh.school.depmonitoring.rs;

import org.junit.Test;
import ru.hh.school.depmonitoring.DepMonitoringTestBase;
import ru.hh.school.depmonitoring.dao.RepositoryLinkDao;
import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RepositoryLinkResourceTest extends DepMonitoringTestBase {
    @Inject
    private DBUtils dbUtils;

    @Inject
    private RepositoryLinkDao repositoryLinkDao;

    @Test
    public void getExistingLinkById() {
        dbUtils.addItemToRepositoryTable(1L);
        dbUtils.addItemToRepositoryLinkTable(1L);
        int id = dbUtils.getFirstExistingIdOfrepositoryLink()
                .orElseThrow();

        RepositoryLinkDto repositoryLinkDto = target("/repository-link/" + id)
                .request()
                .get(RepositoryLinkDto.class);
        assertRepositoryLinkDtoIsEquals(StructCreator.createRepositoryLinkDto(1L), repositoryLinkDto);
    }

    @Test
    public void getNotExistingLinkById() {
        Response response = this.createRequest("/repository-link/100500").get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void getExistingLinkForRepository() {
        long repositoryId = 1L;

        dbUtils.addItemToRepositoryTable(repositoryId);
        dbUtils.addItemToRepositoryLinkTable(repositoryId);
        dbUtils.addItemToRepositoryLinkTable(repositoryId);

        List<RepositoryLinkDto> controlListRepositoryLinkDto = new ArrayList<>();
        controlListRepositoryLinkDto.add(StructCreator.createRepositoryLinkDto(repositoryId));
        controlListRepositoryLinkDto.add(StructCreator.createRepositoryLinkDto(repositoryId));

        List<RepositoryLinkDto> resultListRepositoryLinkDto = target("/repository-link/for-repository/1")
                .request()
                .get()
                .readEntity(new GenericType<List<RepositoryLinkDto>>() {});

        assertRepositoryLinkDtoListIsEquals(controlListRepositoryLinkDto, resultListRepositoryLinkDto);
    }

    @Test
    public void getNotExistingLinkForRepository() {
        List<RepositoryLinkDto> resultListRepositoryLinkDto = target("/repository-link/for-repository/1")
                .request()
                .get()
                .readEntity(new GenericType<List<RepositoryLinkDto>>() {});

        assertTrue(resultListRepositoryLinkDto.isEmpty());
    }

    @Test
    public void addNewLink() {
        assertEquals(0, (int)dbUtils.doInTransaction(()->repositoryLinkDao.count()));
        dbUtils.addItemToRepositoryTable(1L);

        RepositoryLinkDto dto = StructCreator.createRepositoryLinkDto(1L);
        RepositoryLinkDto response = target("/repository-link")
                .request()
                .post(Entity.json(dto))
                .readEntity(RepositoryLinkDto.class);

        assertEquals(1, (int)dbUtils.doInTransaction(()->repositoryLinkDao.count()));
        assertRepositoryLinkDtoIsEquals(dto, response);
    }

    @Test
    public void updateOne() {
        dbUtils.addItemToRepositoryTable(1L);
        dbUtils.addItemToRepositoryLinkTable(1L);
        int id = dbUtils.getFirstExistingIdOfrepositoryLink()
                .orElseThrow();

        RepositoryLinkDto dto = StructCreator.createRepositoryLinkDto();
        dto.setRepositoryLinkId(id);
        Response response = target("/repository-link/" + id)
                .request()
                .put(Entity.json(dto));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(dto, response.readEntity(RepositoryLinkDto.class));
    }

    @Test(expected = IllegalStateException.class)
    public void tryToUpdateNull() {
        RepositoryLinkDto dto = null;
        target("/repository-link/1")
                .request()
                .put(Entity.json(dto));
    }

    @Test
    public void deleteOne() {
        dbUtils.addItemToRepositoryTable(1L);
        dbUtils.addItemToRepositoryLinkTable(1L);
        int id = dbUtils.getFirstExistingIdOfrepositoryLink()
                .orElseThrow();
        assertEquals(1, (int)dbUtils.doInTransaction(()->repositoryLinkDao.count()));

        Response response = target("/repository-link/" + id)
                .request()
                .delete();

        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
        assertEquals(0, (int)dbUtils.doInTransaction(()->repositoryLinkDao.count()));
    }

    private void assertRepositoryLinkDtoIsEquals(RepositoryLinkDto one, RepositoryLinkDto two) {
        assertNotNull(one);
        assertNotNull(two);
        assertEquals(one.getRepositoryId(), two.getRepositoryId());
        assertEquals(one.getLinkType(), two.getLinkType());
        assertEquals(one.getLinkUrl(), two.getLinkUrl());
    }

    private void assertRepositoryLinkDtoListIsEquals(List<RepositoryLinkDto> one, List<RepositoryLinkDto> two) {
        assertEquals(one.size(), two.size());

        for (int i = 0; i < one.size(); i++) {
            assertRepositoryLinkDtoIsEquals(one.get(i), two.get(i));
        }
    }
}
