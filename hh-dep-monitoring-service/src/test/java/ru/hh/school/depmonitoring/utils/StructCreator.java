package ru.hh.school.depmonitoring.utils;

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
}
