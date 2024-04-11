package org.example.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class AbstractEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long capacity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


}
