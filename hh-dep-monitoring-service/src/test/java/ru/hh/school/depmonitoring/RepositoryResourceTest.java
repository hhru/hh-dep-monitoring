package ru.hh.school.depmonitoring;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.utils.DBUtils;
import ru.hh.school.depmonitoring.utils.StructCreator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepositoryResourceTest extends DepMonitoringTestBase {
    @Inject
    private DBUtils dbUtils;

    @Before
    public void fillTable() {
        dbUtils.fillRepositoryTable();
    }

    @Test
    public void getItemByIdTest() {
        RepositoryDto repositoryDto = target("/repository/1")
                .request()
                .get(RepositoryDto.class);
        assertRepositoryDtoIsEquals(StructCreator.createRepositoryDto(), repositoryDto);
    }

    @Test
    public void getNonExistingItemTest() {
        Response response = this.createRequest("/repository/99").get();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void getAllItemsTest() {
        RepositoryDto[] repositoryDtoArray = StructCreator.createRepositoryDtoList().toArray(new RepositoryDto[0]);
        PageDto.PageDtoBuilder<RepositoryDto> builder = PageDto.builder();
        PageDto<RepositoryDto> controlPageDto = PageDto.<RepositoryDto>builder()
                .withItems(repositoryDtoArray)
                .withPerPage(10)
                .withPages(1)
                .withPage(0)
                .withFound(10)
                .build();
        PageDto<RepositoryDto> resultPageDto = target("/repository/")
                .request()
                .get()
                .readEntity(new GenericType<PageDto<RepositoryDto>>() {
                });
        assertRepositoryPageDtoIsEquals(controlPageDto, resultPageDto);
    }

    @Test
    public void putItemWithIdTest() {
        RepositoryDto dto = StructCreator.createRepositoryDto();
        Response response = target("/repository/1")
                .request()
                .acceptEncoding("identity")
                .put(Entity.json(dto));
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }

    @Test
    public void putItemWrongIdTest() {
        RepositoryDto dto = StructCreator.createRepositoryDto();
        Response response = target("/repository/2")
                .request()
                .acceptEncoding("identity")
                .put(Entity.json(dto));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    public static void assertRepositoryDtoIsEquals(RepositoryDto dto1, RepositoryDto dto2) {
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

    public static void assertRepositoryPageDtoIsEquals(PageDto<RepositoryDto> page1, PageDto<RepositoryDto> page2) {
        assertNotNull(page1);
        assertNotNull(page2);
        assertNotNull(page1.getItems());
        assertNotNull(page2.getItems());
        for (int i = 0; i < page1.getItems().length; i++) {
            assertRepositoryDtoIsEquals(page1.getItems()[i], page2.getItems()[i]);
        }
        assertEquals(page1.getFound(), page2.getFound());
        assertEquals(page1.getPage(), page2.getPage());
        assertEquals(page1.getPerPage(), page2.getPerPage());
        assertEquals(page1.getPages(), page2.getPages());
    }
}
