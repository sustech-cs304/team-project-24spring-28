package org.example.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name = "event_id")
    private long eventId;
    private long score;
}
