package ru.hh.school.depmonitoring.utils;

import ru.hh.school.depmonitoring.dto.PageRequestDto.PageSort;
import ru.hh.school.depmonitoring.dto.RelationDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Artifact;
import ru.hh.school.depmonitoring.entities.Event;
import ru.hh.school.depmonitoring.entities.EventType;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.entities.RepositoryLinkType;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.entities.RepositoryType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StructCreator {
    public static RelationDto createRelationDto(Integer id) {
        return createRelationDto(id, 1L, 2L);
    }

    public static RelationDto createRelationDto(Integer id, long from, long to) {
        return RelationDto.builder()
                .withRelationId(id)
                .withRepositoryFromId(from)
                .withRepositoryToId(to)
                .withPriority(RepositoryRelationPriority.CRITICAL)
                .withDescription("Test description")
                .build();
    }

    public static Relation createRelationEntity(Integer id) {
        Relation relation = new Relation();
        relation.setRelationId(id);
        relation.setRepositoryFrom(createRepositoryEntity(1L, LocalDateTime.now()));
        relation.setRepositoryTo(createRepositoryEntity(2L, LocalDateTime.now()));
        relation.setPriority(RepositoryRelationPriority.CRITICAL);
        relation.setDescription("Test description");
        return relation;
    }

    public static GHRepositoryDto createGHRepositoryDto(long id) {
        return GHRepositoryDto.builder()
                .withRepositoryId(id)
                .withName("Repo " + id)
                .withHtmlUrl("http://github.com/test/repo" + id)
                .withDescription("Test repo " + id)
                .withArchived(false)
                .withActive(true)
                .withCreatedAt(new Date())
                .withUpdatedAt(new Date())
                .build();
    }

    public static Repository createRepositoryEntity(long id, LocalDateTime dateTime) {
        Repository repository = new Repository();
        repository.setRepositoryId(id);
        repository.setName("Repo " + id);
        repository.setHtmlUrl("http://github.com/test/repo" + id);
        repository.setDescription("Test repo " + id);
        repository.setArchived(false);
        repository.setActive(true);
        repository.setCreatedAt(LocalDateTime.now());
        repository.setUpdatedAt(dateTime);
        repository.setRepositoryType(RepositoryType.APPLICATION);
        return repository;
    }

    public static List<GHRepositoryDto> createGHRepositoryDtoList(long count) {
        return LongStream.rangeClosed(1, count)
                .mapToObj(StructCreator::createGHRepositoryDto)
                .collect(Collectors.toList());
    }

    public static RepositoryDto createRepositoryDto() {
        return createRepositoryDto(1L);
    }

    public static RepositoryDto createRepositoryDto(long id) {
        return createRepositoryDto(id, String.format("Name%02d", id));
    }

    public static RepositoryDto createRepositoryDto(long id, String name) {
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setRepositoryId(id);
        repositoryDto.setName(name);
        repositoryDto.setHtmlUrl("http://htmlUrl");
        repositoryDto.setDescription("Description");
        repositoryDto.setArchived(true);
        repositoryDto.setActive(false);
        repositoryDto.setCreatedAt(LocalDateTime.of(2019, 3, 12, 14, (int)id%60));
        repositoryDto.setUpdatedAt(LocalDateTime.of(2019, 4, 12, 13, 22));
        repositoryDto.setRepositoryType(RepositoryType.APPLICATION);

        return repositoryDto;
    }

    public static List<RepositoryDto> createRepositoryDtoList() {
        return LongStream.rangeClosed(1, 10)
                .mapToObj(StructCreator::createRepositoryDto)
                .collect(Collectors.toList());
    }

    public static RepositoryLinkDto createRepositoryLinkDto() {
        return createRepositoryLinkDto(1L);
    }

    public static RepositoryLinkDto createRepositoryLinkDto(long repId) {
        RepositoryLinkDto repositoryLinkDto = new RepositoryLinkDto();
        repositoryLinkDto.setRepositoryLinkId(null);
        repositoryLinkDto.setLinkType(RepositoryLinkType.JENKINS);
        repositoryLinkDto.setRepositoryId(repId);
        repositoryLinkDto.setLinkUrl("127.0.0.1:8080");
        return repositoryLinkDto;
    }

    public static RepositoryLink createRepositoryLinkEntity(long id, String url, RepositoryLinkType type) {
        RepositoryLink repositoryLink = new RepositoryLink();
        repositoryLink.setRepositoryLinkId(null);
        repositoryLink.setLinkType(type);
        repositoryLink.setRepositoryId(id);
        repositoryLink.setLinkUrl(url);
        return repositoryLink;
    }

    public static Event createEventEntity() {
        Event event = new Event();
        event.setDescription("Description");
        event.setType(EventType.CONFLICT);
        event.setCreatedAt(LocalDateTime.now());

        return event;
    }



    public static Artifact createArtifactEntity() {
        Artifact entity = new Artifact();

        entity.setArtifactId(null);
        entity.setArtifactName("artifact name");
        entity.setGroupName("group name ");

        return entity;
    }


    public static String getEncodedString(PageSort pageSort) throws UnsupportedEncodingException {
        return URLEncoder.encode(pageSort.getProperty() + "," + (pageSort.isAscending() ? "asc" : "desc"), "UTF-8");
    }

}
