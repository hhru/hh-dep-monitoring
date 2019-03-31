package ru.hh.school.depmonitoring.utils;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;

import javax.annotation.Nonnull;
import java.util.List;

public class DBUtils {
    private final SessionFactory sessionFactory;
    private final RepositoryMapper repositoryMapper;

    public DBUtils(SessionFactory sessionFactory, RepositoryMapper repositoryMapper) {
        this.repositoryMapper = repositoryMapper;
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public int cleanTable(@Nonnull Class clazz) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("delete from " + clazz.getName())
                .executeUpdate();
    }

    @Transactional
    public void fillRepositoryTable() {
        List<RepositoryDto> repositoryDtoList = StructCreator.createRepositoryDtoList();
        for (RepositoryDto dto : repositoryDtoList) {
            sessionFactory.getCurrentSession().persist(repositoryMapper.toEntity(dto));
        }
    }
}
