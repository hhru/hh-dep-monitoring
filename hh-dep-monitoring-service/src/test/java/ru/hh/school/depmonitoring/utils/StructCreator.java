package ru.hh.school.depmonitoring.utils;

import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StructCreator {
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
        return repository;
    }

    public static List<GHRepositoryDto> createGHRepositoryDtoList(long count) {
        return LongStream.rangeClosed(1, count)
                .mapToObj(StructCreator::createGHRepositoryDto)
                .collect(Collectors.toList());
    }

    public static RepositoryDto createRepositoryDto() {
        RepositoryDto dto = createRepositoryDto(1L);
        return dto;
    }

    public static RepositoryDto createRepositoryDto(long id) {
        RepositoryDto repositoryDto = new RepositoryDto();
        repositoryDto.setRepositoryId(id);
        repositoryDto.setName("Name");
        repositoryDto.setHtmlUrl("http://htmlUrl");
        repositoryDto.setDescription("Description");
        repositoryDto.setArchived(true);
        repositoryDto.setActive(false);
        repositoryDto.setCreatedAt(LocalDateTime.of(2019, 03, 12, 14, 51));
        repositoryDto.setUpdatedAt(LocalDateTime.of(2019, 04, 12, 13, 22));
        return repositoryDto;
    }

    public static List<RepositoryDto> createRepositoryDtoList() {
        return LongStream.rangeClosed(1, 10)
                .mapToObj(StructCreator::createRepositoryDto)
                .collect(Collectors.toList());
    }

}
