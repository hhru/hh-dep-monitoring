package ru.hh.school.depmonitoring.dao.impl;

import org.hibernate.SessionFactory;
import ru.hh.school.depmonitoring.dao.AbstractDao;
import ru.hh.school.depmonitoring.dao.EventDao;
import ru.hh.school.depmonitoring.entities.Event;

public class EventDaoImpl extends AbstractDao<Event, Integer> implements EventDao {
    public EventDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Event.class);
    }

}
