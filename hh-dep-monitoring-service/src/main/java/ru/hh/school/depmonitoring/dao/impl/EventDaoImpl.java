package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.entities.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class EventDaoImpl extends AbstractDao<Event, Integer> implements EventDao {
    public EventDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Event.class);
    }

    @Override
    public String getDefaultFilterField() {
        return "description";
    }

    public Optional<LocalDateTime> getLastEventForRepository(Long repositoryId) {
        return getSession()
                .createQuery("select createdAt from Event where repositoryId = :repositoryId order by createdAt desc", LocalDateTime.class)
                .setParameter("repositoryId", repositoryId)
                .setMaxResults(1)
                .uniqueResultOptional();
    }

    public List<Event> getAllEventsForRepository(long repositoryId) {
        return getSession()
                .createQuery("from Event where repositoryId = :repositoryId order by createdAt desc", Event.class)
                .setParameter("repositoryId", repositoryId)
                .getResultList();
    }

    public List<Event> getLastEventsForRepository(long repositoryId, int eventsCount) {
        return getSession()
                .createQuery("from Event where repositoryId = :repositoryId order by createdAt desc", Event.class)
                .setParameter("repositoryId", repositoryId)
                .setMaxResults(eventsCount)
                .getResultList();
    }
}
