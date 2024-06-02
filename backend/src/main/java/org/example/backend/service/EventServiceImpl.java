package org.example.backend.service;

import org.checkerframework.checker.units.qual.A;
import org.example.backend.api.AbstractEnrollmentRepository;
import org.example.backend.api.EnrollFormRepository;
import org.example.backend.api.EventRepository;
import org.example.backend.api.ScoreRepository;
import org.example.backend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EnrollFormRepository enrollFormRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    AbstractEnrollmentRepository abstractEnrollmentRepository;

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

    @Override
    public long getScore(long userId, long eventId) {
        Score score = scoreRepository.findScoreByUserIdAndEventId(userId, eventId);
        if (score == null) {
            return 0;
        }
        return score.getScore();
    }

    @Override
    public boolean saveScore(long userId, long eventId, long score) {
        Score scoreObj = new Score();
        scoreObj.setUserId(userId);
        scoreObj.setEventId(eventId);
        scoreObj.setScore(score);
        scoreRepository.save(scoreObj);
        return true;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
        return true;
    }

    @Override
    public boolean appliedByUser(long userId, long eventId) {
        return abstractEnrollmentRepository.findAbstractEnrollmentByEventIdAndParticipantsId(eventId, userId) != null;
    }

    @Override
    public List<Event> searchEvent(String keyword) {
        return eventRepository.findAllByTextContainingIgnoreCaseOrTitleContainingIgnoreCase(keyword, keyword);
    }
}