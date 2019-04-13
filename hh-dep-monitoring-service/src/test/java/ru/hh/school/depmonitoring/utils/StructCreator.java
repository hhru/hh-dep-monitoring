package ru.hh.school.depmonitoring.utils;

import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.RepositoryRelationPriority;
import ru.hh.school.depmonitoring.entities.Relation;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.entities.RepositoryType;
import ru.hh.school.depmonitoring.entities.RepositoryLinkType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import ru.hh.school.depmonitoring.dto.RelationDto;

public class StructCreator {
    public static RelationDto createRelationDto(Integer id) {
        return RelationDto.builder()
                .withRelationId(id)
                .withRepositoryFromId(1L)
                .withRepositoryToId(2L)
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
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setRepositoryId(id);
        repositoryDto.setName("Name");
        repositoryDto.setHtmlUrl("http://htmlUrl");
        repositoryDto.setDescription("Description");
        repositoryDto.setArchived(true);
        repositoryDto.setActive(false);
        repositoryDto.setCreatedAt(LocalDateTime.of(2019, 3, 12, 14, 51));
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
}
