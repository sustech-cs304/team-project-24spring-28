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

    private String title;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private EventType type;
    private String introduction;
    private String text;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String posterUrl;
    private float score;
    private long scoreCount;

    @ManyToMany(mappedBy = "favouriteEvents")
    private List<User> collectors;

    @OneToOne
    @JoinColumn(name = "abstractEnrollment_id")
    private AbstractEnrollment abstractEnrollment;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public long getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(long scoreCount) {
        this.scoreCount = scoreCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
