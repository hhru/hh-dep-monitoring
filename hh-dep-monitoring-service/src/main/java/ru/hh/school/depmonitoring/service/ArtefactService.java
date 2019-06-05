package ru.hh.school.depmonitoring.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dao.DependencyDao;
import ru.hh.school.depmonitoring.dao.RepositoryDao;
import ru.hh.school.depmonitoring.dto.ArtefactTreeDto;
import ru.hh.school.depmonitoring.dto.DependencyDto;
import ru.hh.school.depmonitoring.entities.Repository;
import ru.hh.school.depmonitoring.service.mapper.DependencyMapper;

import java.util.ArrayList;
import java.util.List;

public class ArtefactService {
    private final RepositoryDao repositoryDao;
    private final DependencyDao dependencyDao;
    private final DependencyMapper dependencyMapper;

    public ArtefactService(RepositoryDao repositoryDao, DependencyDao dependencyDao, DependencyMapper dependencyMapper) {
        this.repositoryDao = repositoryDao;
        this.dependencyDao = dependencyDao;
        this.dependencyMapper = dependencyMapper;
    }

    @Transactional(readOnly = true)
    public List<ArtefactTreeDto> getFullTree() {
        List<ArtefactTreeDto> result = new ArrayList<>();

        for (Repository repository : repositoryDao.findAll()) {
            ArtefactTreeDto artefactTreeDto = new ArtefactTreeDto();
            artefactTreeDto.setRepositoryId(repository.getRepositoryId());
            artefactTreeDto.setName(repository.getName());
            artefactTreeDto.setDescription(repository.getDescription());
            artefactTreeDto.setHtmlUrl(repository.getHtmlUrl());
            artefactTreeDto.setArchived(repository.isArchived());
            artefactTreeDto.setUpdatedAt(repository.getUpdatedAt());
            artefactTreeDto.setRepositoryType(repository.getRepositoryType());

            List<DependencyDto> dependencies = getDependencyTreeForRepository(repository);

            if (dependencies != null && !dependencies.isEmpty()) {
                artefactTreeDto.setDependencies(dependencies);
                result.add(artefactTreeDto);
            }
        }
        return result;
    }

    private List<DependencyDto> getDependencyTreeForRepository(Repository repository) {

        List<DependencyDto> dependencies = dependencyMapper.toDto(
                dependencyDao.findRepositoryFirstLevel(repository));
        setDependencyChildren(dependencies);

        return dependencies;
    }

    private void setDependencyChildren(List<DependencyDto> dependencies) {
        if (dependencies == null) {
            return;
        }
        for (DependencyDto dependencyDto : dependencies) {
            List<DependencyDto> children = dependencyMapper.toDto(
                    dependencyDao.findByParentDependency(
                            dependencyMapper.toEntity(dependencyDto)
                    ));
            setDependencyChildren(children);
            dependencyDto.setChildren(children);
        }
    }
}
