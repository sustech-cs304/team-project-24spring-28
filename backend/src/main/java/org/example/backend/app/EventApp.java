package org.example.backend.app;

import org.example.backend.domain.*;
import org.example.backend.dto.DefinedFormDto;
import org.example.backend.dto.EventBriefDto;
import org.example.backend.dto.EventDto;
import org.example.backend.dto.EventPostDto;
import org.example.backend.service.EventService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventApp {
    @Autowired
    EventService eventService;

    @PostMapping("/create")
    public boolean releaseEvent(@RequestHeader("Authorization") String token, @RequestParam EventPostDto eventPostDto) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = new Event();
        event.setTitle(eventPostDto.getTitle());
        event.setName(eventPostDto.getName());
        event.setAuthor(user);
        event.setIntroduction(eventPostDto.getIntroduction());
        event.setText(eventPostDto.getMdText());
        event.setStartTime(eventPostDto.getStartTime());
        event.setEndTime(eventPostDto.getEndTime());
        event.setPosterUrl(eventPostDto.getImageUrl());
        switch (eventPostDto.getEnrollmentType()) {
            case "count":
                CountEnrollment countEnrollment = new CountEnrollment();
                countEnrollment.setStartTime(eventPostDto.getApplyStartTime());
                countEnrollment.setEndTime(eventPostDto.getApplyEndTime());
                countEnrollment.setCapacity(eventPostDto.getLimitCount());
                event.setAbstractEnrollment(countEnrollment);
                break;
            case "form":
                FormEnrollment formEnrollment = new FormEnrollment();
                formEnrollment.setStartTime(eventPostDto.getApplyStartTime());
                formEnrollment.setEndTime(eventPostDto.getApplyEndTime());
                formEnrollment.setDefinedFormEntries(eventPostDto.getDefinedForm().stream().map(DefinedFormDto::toDefinedFormEntry).toList());
                event.setAbstractEnrollment(formEnrollment);
                break;
        }
        return eventService.saveEvent(event);
    }

    @GetMapping("/detail")
    public EventDto getEvent(@RequestParam("id") long eventId) {
        Event event = eventService.findEventById(eventId);
        if (event == null) {
            return null;
        }
        return new EventDto(event);
    }

    @GetMapping("/brief")
    public EventBriefDto getEventBrief(@RequestParam("id") long eventId) {
        Event event = eventService.findEventById(eventId);
        if (event == null) {
            return null;
        }
        return new EventBriefDto(event);
    }

    @GetMapping("/hold")
    public List<EventBriefDto> getEventByAuthorId(@RequestHeader("Authorization") String token) {
        long authorId = JwtUtil.verifyToken(token).getId();
        return eventService.findEventByAuthorId(authorId).stream().map(EventBriefDto::new).toList();
    }

    @PostMapping("/apply")
    public boolean applyEvent(@RequestHeader("Authorization") String token, @RequestParam("id") long eventId, @RequestParam("formValues") List<String> formValues) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = eventService.findEventById(eventId);
        EnrollForm enrollForm = new EnrollForm();
        enrollForm.setUser(user);
        enrollForm.setFormEnrollment((FormEnrollment) event.getAbstractEnrollment());
        enrollForm.setFormValues(formValues);
        return eventService.saveEnrollForm(enrollForm);
    }
}
