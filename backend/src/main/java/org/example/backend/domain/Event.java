package org.example.backend.domain;

import jakarta.persistence.*;
import org.example.backend.domain.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;


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

    @ManyToMany(mappedBy = "favouriteEvents")
    private List<User> collectors;

    @OneToOne
    @JoinColumn(name = "abstractEnrollment_id")
    private AbstractEnrollment abstractEnrollment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<User> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<User> collectors) {
        this.collectors = collectors;
    }

    public AbstractEnrollment getAbstractEnrollment() {
        return abstractEnrollment;
    }

    public void setAbstractEnrollment(AbstractEnrollment abstractEnrollment) {
        this.abstractEnrollment = abstractEnrollment;
    }
}
