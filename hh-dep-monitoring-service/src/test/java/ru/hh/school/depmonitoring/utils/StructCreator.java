package ru.hh.school.depmonitoring.utils;

import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        List<GHRepositoryDto> repositoryDtoList= new LinkedList<>();
        for (long i = 1; i <= count; i++) {
            repositoryDtoList.add(StructCreator.createGHRepositoryDto(i));
        }
        return repositoryDtoList;
    }
}
