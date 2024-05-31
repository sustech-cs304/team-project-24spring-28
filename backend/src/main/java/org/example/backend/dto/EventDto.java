package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.domain.AbstractEnrollment;
import org.example.backend.domain.CountEnrollment;
import org.example.backend.domain.Event;
import org.example.backend.domain.FormEnrollment;
import org.example.backend.domain.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private long id;
    private String title;
    private String eventName;
    private long authorId;
    private String authorName;
    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float score;
    private long grade;
    private String postUrl;
    private boolean liked;
    private String text;
    private EventType eventType;
    private String enrollmentType;
    private Long currentCount;
    private Long limit;
    private List<DefinedFormDto> definedForm;
    private long[] postList;

    // postList grade liked are not set
    public EventDto(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.eventName = event.getName();
        this.authorId = event.getAuthor().getId();
        this.applyStartTime = event.getAbstractEnrollment().getStartTime();
        this.applyEndTime = event.getAbstractEnrollment().getEndTime();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.score = event.getScore();
        this.postUrl = event.getPosterUrl();
        this.text = event.getText();
        this.eventType = event.getType();
        AbstractEnrollment enrollment = event.getAbstractEnrollment();
        if (enrollment instanceof CountEnrollment) {
            this.enrollmentType = "count";
            this.currentCount = ((CountEnrollment) enrollment).getCount();
            this.limit = ((CountEnrollment) enrollment).getCapacity();
        }
        if (enrollment instanceof FormEnrollment) {
            this.enrollmentType = "form";
            this.definedForm = ((FormEnrollment) enrollment).getDefinedFormEntries().stream().map(DefinedFormDto::new).collect(Collectors.toList());
        }
    }
}
