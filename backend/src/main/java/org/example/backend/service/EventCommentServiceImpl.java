package org.example.backend.service;

import org.example.backend.api.EventCommentRepository;
import org.example.backend.domain.EventComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCommentServiceImpl implements EventCommentService {
    @Autowired
    EventCommentRepository eventCommentRepository;

    @Override
    public EventComment findEventCommentById(long id) {
        return eventCommentRepository.findById(id).orElse(null);
    }

    @Override
    public List<EventComment> findEventCommentByEventId(long id) {
        return eventCommentRepository.findEventCommentByEventId(id);
    }

    @Override
    public EventComment saveEventComment(EventComment eventComment) {
        return eventCommentRepository.save(eventComment);
    }

    @Override
    public List<EventComment> findAllById(Long[] ids) {
        return eventCommentRepository.findAllById(List.of(ids));
    }

    @Override
    public List<EventComment> findAllByUserId(long id) {
        return eventCommentRepository.findAllByUserId(id);
    }

    @Override
    public List<EventComment> findEventCommentByPostId(long postId) {
        return eventCommentRepository.findEventCommentByPostId(postId);
    }

    @Override
    public boolean deleteEventComment(long commentId) {
        eventCommentRepository.deleteById(commentId);
        return true;
    }
}
