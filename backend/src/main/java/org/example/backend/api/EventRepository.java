package org.example.backend.api;

import org.example.backend.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventByAuthorId(long authorId);
}
