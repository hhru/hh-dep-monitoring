package ru.hh.school.depmonitoring.utils;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.depmonitoring.dto.RepositoryDto;
import ru.hh.school.depmonitoring.service.mapper.RepositoryMapper;

import javax.annotation.Nonnull;
import javax.persistence.Table;
import java.util.List;
import java.util.function.Supplier;

public class DBUtils {
    private final RepositoryMapper repositoryMapper;
    private final SessionFactory sessionFactory;

    public DBUtils(RepositoryMapper repositoryMapper, SessionFactory sessionFactory) {
        this.repositoryMapper = repositoryMapper;
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public int cleanTable(@Nonnull Class clazz) {
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery("truncate table " + tableName + " RESTART IDENTITY CASCADE")
                .executeUpdate();
    }

    @Transactional
    public void fillRepositoryTable() {
        List<RepositoryDto> repositoryDtoList = StructCreator.createRepositoryDtoList();
        for (RepositoryDto dto : repositoryDtoList) {
            sessionFactory.getCurrentSession().persist(repositoryMapper.toEntity(dto));
        }
    }

    @Transactional
    public <T> T doInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }
}
