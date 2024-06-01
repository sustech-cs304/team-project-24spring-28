package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.domain.Event;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventBriefDto {
    private String title;
    private String eventName;
    private long authorId;
    private String authorName;
    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float score;
    private String postUrl;
    private String introduction;

    public EventBriefDto(Event event) {
        this.title = event.getTitle();
        this.eventName = event.getName();
        this.authorId = event.getAuthor().getId();
        this.authorName = event.getAuthor().getName();
        this.applyStartTime = event.getAbstractEnrollment().getStartTime();
        this.applyEndTime = event.getAbstractEnrollment().getEndTime();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.score = event.getScore();
        this.postUrl = event.getPosterUrl();
        this.introduction = event.getIntroduction();
    }
}
