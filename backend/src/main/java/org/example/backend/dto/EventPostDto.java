package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.domain.Event;

import java.util.List;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventPostDto {
    private String title;
    private String name;
    private String enrollmentType;
    private LocalDateTime applyStartTime;
    private LocalDateTime applyEndTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String imageUrl;
    private String introduction;
    private String mdText;
    private long limitCount;
    private List<DefinedFormDto> definedForm;
}
