package ru.hh.school.depmonitoring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.RepositoryLinkDao;
import ru.hh.school.depmonitoring.dto.RepositoryLinkDto;
import ru.hh.school.depmonitoring.entities.RepositoryLink;
import ru.hh.school.depmonitoring.service.mapper.RepositoryLinkMapper;

import java.util.List;
import java.util.Optional;

public class RepositoryLinkService {

    private final RepositoryLinkDao repositoryLinkDao;
    private final RepositoryLinkMapper repositoryLinkMapper;

    public RepositoryLinkService(RepositoryLinkDao repositoryLinkDao, RepositoryLinkMapper repositoryLinkMapper) {
        this.repositoryLinkDao = repositoryLinkDao;
        this.repositoryLinkMapper = repositoryLinkMapper;
    }

    @Transactional(readOnly = true)
    public Optional<RepositoryLinkDto> findRepositoryLinkById(int id) {
        return repositoryLinkDao
                .findOne(id)
                .map(repositoryLinkMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<RepositoryLinkDto> findForRepository(long repositoryId) {
        return repositoryLinkMapper
                .toDto(repositoryLinkDao.findForRepository(repositoryId));
    }

    @Transactional
    public RepositoryLinkDto update(RepositoryLinkDto repositoryLinkDto) {
        RepositoryLink currentRepositoryLink = repositoryLinkDao
                .findOne(repositoryLinkDto.getRepositoryLinkId())
                .orElseThrow(() -> new IllegalArgumentException("RepositoryLink with id = "
                        + repositoryLinkDto.getRepositoryLinkId() + "not found!"));

        currentRepositoryLink.setLinkUrl(repositoryLinkDto.getLinkUrl());
        currentRepositoryLink.setLinkType(repositoryLinkDto.getLinkType());

        repositoryLinkDao.update(currentRepositoryLink);
        return repositoryLinkMapper.toDto(currentRepositoryLink);
    }

    @Transactional
    public RepositoryLinkDto saveRepositoryLink(RepositoryLinkDto repositoryLinkDto) {
        RepositoryLink repositoryLink = repositoryLinkMapper.toEntity(repositoryLinkDto);
        repositoryLinkDao.create(repositoryLink);

        return repositoryLinkMapper.toDto(repositoryLink);
    }

    @Transactional
    public void deleteById(int id) {
        repositoryLinkDao.deleteById(id);
    }

}
