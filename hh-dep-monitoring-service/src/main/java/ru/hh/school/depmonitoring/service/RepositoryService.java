package ru.hh.school.depmonitoring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

public class RepositoryService {

    private final RepositoryDao repositoryDao;
    private final RepositoryMapper repositoryMapper;

    public RepositoryService(RepositoryDao repositoryDao, RepositoryMapper repositoryMapper) {
        this.repositoryDao = repositoryDao;
        this.repositoryMapper = repositoryMapper;
    }

    @Transactional(readOnly = true)
    public List<RepositoryDto> getFullList() {
        return repositoryDao.findAll().stream()
                .map(repositoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<RepositoryDto> getOneItem(@Nonnull Long id) {
        Optional<Repository> repository = repositoryDao.findOne(id);
        return repository.map(repositoryMapper::toDto);
    }

    @Transactional
    public void update(@Nonnull RepositoryDto dto) {
        repositoryDao.update(repositoryMapper.toEntity(dto));
    }

}
