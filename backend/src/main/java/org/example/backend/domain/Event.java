package org.example.backend.domain;

import jakarta.persistence.*;
import org.example.backend.domain.enums.EventType;

import java.time.LocalDateTime;


@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private EventType type;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToOne
    private AbstractEnrollment abstractEnrollment;


}
