package ru.hh.school.depmonitoring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.PageDto;
import ru.hh.school.depmonitoring.dto.PageRequestDto;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;

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
    public RepositoryDto update(@Nonnull RepositoryDto dto) {
        Repository currentRepository = repositoryDao
                .findOne(dto.getRepositoryId())
                .orElseThrow(() -> new IllegalArgumentException("Repository with id = "
                        + dto.getRepositoryId() + "not found!"));

        currentRepository.setDescription(dto.getDescription());
        currentRepository.setName(dto.getName());
        currentRepository.setActive(dto.isActive());
        currentRepository.setArchived(dto.isArchived());

        repositoryDao.update(currentRepository);
        return repositoryMapper.toDto(currentRepository);
    }

    @Transactional(readOnly = true)
    public PageDto<RepositoryDto> getRepositoryPage(@Nonnull PageRequestDto pageRequestDto) {
        PageDto.PageDtoBuilder<RepositoryDto> builder = PageDto.builder();
        if (pageRequestDto.getPerPage() <= 0 || pageRequestDto.getPage() < 0) {
            throw new IllegalArgumentException("Illegal pageRequestDto parameters");
        }
        List<RepositoryDto> repositoryDtos = repositoryDao.findPage(pageRequestDto).stream()
                .map(repositoryMapper::toDto)
                .collect(Collectors.toList());
        builder = builder.withItems(repositoryDtos);
        int found = repositoryDao.count();
        int pages = (int) Math.ceil((double) found / pageRequestDto.getPerPage());
        return builder.withFound(found)
                .withPage(pageRequestDto.getPage())
                .withPerPage(pageRequestDto.getPerPage())
                .withPages(pages)
                .build();
    }

}
