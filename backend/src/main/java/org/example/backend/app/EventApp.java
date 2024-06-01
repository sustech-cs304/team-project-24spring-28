package org.example.backend.app;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.backend.config.MyException;
import org.example.backend.domain.*;
import org.example.backend.dto.DefinedFormDto;
import org.example.backend.dto.EventBriefDto;
import org.example.backend.dto.EventDto;
import org.example.backend.dto.EventPostDto;
import org.example.backend.service.AbstractEnrollmentService;
import org.example.backend.service.AbstractUserService;
import org.example.backend.service.EventService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventApp {
    @Autowired
    EventService eventService;
    @Autowired
    AbstractEnrollmentService abstractEnrollmentService;

    @PostMapping("/create")
//    public boolean releaseEvent(@RequestHeader("Authorization") String token, @RequestParam String title, @RequestParam String name, @RequestParam String enrollmentType, @RequestParam String applyStartTime, @RequestParam String applyEndTime, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String imageUrl, @RequestParam String introduction, @RequestParam String mdText, @RequestParam(required = false) long limitCount, @RequestParam(required = false) List<DefinedFormDto> definedForm) {
    public boolean releaseEvent(@RequestHeader("Authorization") String token, @RequestBody EventPostDto eventPostDto) {
        User user = (User) JwtUtil.verifyToken(token);
        if (!user.getPermission().isCanCreate()) {
            throw new MyException(-1, "Permission denied");
        }
        throw new MyException(-1, eventPostDto.getStartTime()+" "+eventPostDto.getTitle());
//        Event event = new Event();
//        event.setTitle(eventPostDto.getTitle());
//        event.setName(eventPostDto.getName());
//        event.setAuthor(user);
//        event.setIntroduction(eventPostDto.getIntroduction());
//        event.setPosterUrl(eventPostDto.getImageUrl());
//        event.setText(eventPostDto.getMdText());
//        event.setStartTime(LocalDateTime.parse(eventPostDto.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        event.setEndTime(LocalDateTime.parse(eventPostDto.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        switch (eventPostDto.getEnrollmentType()) {
//            case "count":
//                CountEnrollment countEnrollment = new CountEnrollment();
//                countEnrollment.setStartTime(LocalDateTime.parse(eventPostDto.getApplyStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                countEnrollment.setEndTime(LocalDateTime.parse(eventPostDto.getApplyEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                countEnrollment.setCapacity(eventPostDto.getLimitCount());
//                event.setAbstractEnrollment(countEnrollment);
//                break;
//            case "form":
//                FormEnrollment formEnrollment = new FormEnrollment();
//                formEnrollment.setStartTime(LocalDateTime.parse(eventPostDto.getApplyStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                formEnrollment.setEndTime(LocalDateTime.parse(eventPostDto.getApplyEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//                formEnrollment.setDefinedFormEntries(eventPostDto.getDefinedForm().stream().map(DefinedFormDto::toDefinedFormEntry).toList());
//                event.setAbstractEnrollment(formEnrollment);
//                break;
//        }
//        return eventService.saveEvent(event);
    }

    @GetMapping("/all")
    public long[] getAllEvents() {
        return eventService.findAllEvents().stream().mapToLong(Event::getId).toArray();
    }

    @GetMapping("/detail")
    public EventDto getEvent(@RequestHeader("Authorization") String token, @RequestParam("id") long eventId) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = eventService.findEventById(eventId);
        if (event == null) {
            return null;
        }
        EventDto eventDto = new EventDto(event);
        eventDto.setLiked(user.getFavouriteEvents().contains(event));
        eventDto.setGrade(eventService.getScore(user.getId(), eventId));
        return eventDto;
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
        if (!user.getPermission().isCanEnroll()) {
            throw new MyException(-1, "Permission denied");
        }
        Event event = eventService.findEventById(eventId);
        EnrollForm enrollForm = new EnrollForm();
        AbstractEnrollment abstractEnrollment = event.getAbstractEnrollment();
        if (abstractEnrollment instanceof CountEnrollment countEnrollment) {
            if (countEnrollment.getCount() >= countEnrollment.getCapacity()) {
                throw new MyException(-1, "Capacity full");
            } else {
                countEnrollment.setCount(countEnrollment.getCount() + 1);
                List<User> participants = countEnrollment.getParticipants();
                participants.add(user);
            }
        }
        if (abstractEnrollment instanceof FormEnrollment formEnrollment) {
            if (formValues.size() != formEnrollment.getDefinedFormEntries().size()) {
                throw new MyException(-1, "Form values not match");
            } else {
                List<User> participants = formEnrollment.getParticipants();
                participants.add(user);
                enrollForm.setUser(user);
                enrollForm.setFormEnrollment((FormEnrollment) event.getAbstractEnrollment());
                enrollForm.setFormValues(formValues);
                eventService.saveEnrollForm(enrollForm);
            }
        }
        abstractEnrollmentService.updateAbstractEnrollment(abstractEnrollment);
        return true;
    }

    @GetMapping("/getExcel")
    @ResponseBody
    public void getExcel(@RequestParam("id") long id, HttpServletResponse response) {
        Event event = eventService.findEventById(id);
        AbstractEnrollment abstractEnrollment = event.getAbstractEnrollment();
        if (!(abstractEnrollment instanceof CountEnrollment) && !(abstractEnrollment instanceof FormEnrollment)) {
            throw new MyException(0, "Unsupported enrollment type");
        }
        String fileName = "participants.xlsx";
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet();
            List<String> headers = new ArrayList<>(List.of("Username", "Name"));
            int rowNum = 0;
            XSSFRow row = sheet.createRow(rowNum);
            if (abstractEnrollment instanceof FormEnrollment) {
                for (DefinedFormEntry definedFormEntry : ((FormEnrollment) abstractEnrollment).getDefinedFormEntries()) {
                    headers.add(definedFormEntry.getName());
                }
                for (int i = 0; i < headers.size(); i++) {
                    row.createCell(i).setCellValue(headers.get(i));
                }
                List<EnrollForm> enrollForms = ((FormEnrollment) abstractEnrollment).getEnrollForms();
                for (EnrollForm enrollForm : enrollForms) {
                    row = sheet.createRow(++rowNum);
                    row.createCell(0).setCellValue(enrollForm.getUser().getUsername());
                    row.createCell(1).setCellValue(enrollForm.getUser().getName());
                    List<String> formValues = enrollForm.getFormValues();
                    for (int i = 0; i < formValues.size(); i++) {
                        row.createCell(i + 2).setCellValue(formValues.get(i));
                    }
                }
            } else {
                for (int i = 0; i < headers.size(); i++) {
                    row.createCell(i).setCellValue(headers.get(i));
                }
                List<User> participants = abstractEnrollment.getParticipants();
                for (User participant : participants) {
                    row = sheet.createRow(++rowNum);
                    row.createCell(0).setCellValue(participant.getUsername());
                    row.createCell(1).setCellValue(participant.getName());
                }
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/favor")
    public boolean favorEvent(@RequestHeader("Authorization") String token, @RequestParam("id") long eventId) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = eventService.findEventById(eventId);
        List<Event> favoriteEvents = user.getFavouriteEvents();
        if (!favoriteEvents.contains(event)) {
            favoriteEvents.add(event);
        } else {
            throw new MyException(-1, "Event already favored");
        }
        user.setFavouriteEvents(favoriteEvents);
        return eventService.updateEvent(event);
    }

    @PostMapping("/unfavor")
    public boolean unfavorEvent(@RequestHeader("Authorization") String token, @RequestParam("id") long eventId) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = eventService.findEventById(eventId);
        List<Event> favoriteEvents = user.getFavouriteEvents();
        if (favoriteEvents.contains(event)) {
            favoriteEvents.remove(event);
        } else {
            throw new MyException(-1, "Event not favored");
        }
        user.setFavouriteEvents(favoriteEvents);
        return eventService.updateEvent(event);
    }

    @PostMapping("/grade")
    public boolean gradeEvent(@RequestHeader("Authorization") String token, @RequestParam("id") long eventId, @RequestParam("grade") int grade) {
        User user = (User) JwtUtil.verifyToken(token);
        Event event = eventService.findEventById(eventId);
        List<Event> scoredEvents = user.getScoredEvents();
        if (!scoredEvents.contains(event)) {
            event.setScore((event.getScore() * event.getScoreCount() + grade) / (event.getScoreCount() + 1));
            event.setScoreCount(event.getScoreCount() + 1);
        } else {
            long previousScore = eventService.getScore(user.getId(), eventId);
            event.setScore((event.getScore() * event.getScoreCount() - previousScore + grade) / event.getScoreCount());
        }
        eventService.saveScore(user.getId(), eventId, grade);
        eventService.updateEvent(event);
        return true;
    }

    @GetMapping("/applied")
    public long[] getAppliedEvents(@RequestHeader("Authorization") String token) {
        User user = (User) JwtUtil.verifyToken(token);
        return user.getEnrollments().stream().mapToLong(enrollment -> enrollment.getEvent().getId()).toArray();
    }

    @GetMapping("favored")
    public long[] getFavoredEvents(@RequestHeader("Authorization") String token) {
        User user = (User) JwtUtil.verifyToken(token);
        return user.getFavouriteEvents().stream().mapToLong(Event::getId).toArray();
    }
}
