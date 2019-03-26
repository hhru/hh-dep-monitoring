package ru.hh.school.depmonitoring.utils;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;

public class DBUtils {
    private final SessionFactory sessionFactory;

    public DBUtils(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public int cleanTable(@Nonnull Class clazz) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("delete from " + clazz.getName())
                .executeUpdate();
    }

}
