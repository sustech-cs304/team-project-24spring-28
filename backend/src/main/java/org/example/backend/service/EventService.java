package org.example.backend.service;

import org.example.backend.domain.Event;

public interface EventService {
    boolean saveEvent(Event event);

    boolean deleteEventById(long id);

    boolean updateEvent(Event event);

    Event findEventById(long id);
}
