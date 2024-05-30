package org.example.backend.service;

import org.checkerframework.checker.units.qual.A;
import org.example.backend.api.EnrollFormRepository;
import org.example.backend.api.EventRepository;
import org.example.backend.domain.EnrollForm;
import org.example.backend.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EnrollFormRepository enrollFormRepository;

    @Override
    public boolean saveEvent(Event event) {
        eventRepository.save(event);
        return true;
    }

    @Override
    public boolean deleteEventById(long id) {
        eventRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateEvent(Event event) {
        eventRepository.save(event);
        return true;
    }

    @Override
    public Event findEventById(long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEventByAuthorId(long authorId) {
        return eventRepository.findEventByAuthorId(authorId);
    }

    @Override
    public boolean saveEnrollForm(EnrollForm enrollForm) {
        enrollFormRepository.save(enrollForm);
        return true;
    }
}
