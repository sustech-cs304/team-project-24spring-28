package org.example.backend.app;

import org.example.backend.config.MyException;
import org.example.backend.domain.Permission;
import org.example.backend.domain.User;
import org.example.backend.dto.UserDto;
import org.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminApp {
    @Autowired
    AbstractUserService abstractUserService;
    @Autowired
    EventService eventService;
    @Autowired
    EventCommentService eventCommentService;
    @Autowired
    ReplyCommentService replyCommentService;
    @Autowired
    PostService postService;
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return abstractUserService.findAllUsers().stream().map(UserDto::new).toList();
    }

    @PostMapping("/permission")
    public boolean changePermission(long userId, @RequestParam("canCreate") boolean canCreate, @RequestParam("canEnroll") boolean canEnroll, @RequestParam("canComment") boolean canComment) {
        User user = (User) abstractUserService.findUserById(userId);
        if (user == null) {
            throw new MyException(-1, "User not found");
        }
        Permission permission = user.getPermission();
        if (permission == null) {
            permission = new Permission();
            permission.setUser(user);
        }
        permission.setCanCreate(canCreate);
        permission.setCanEnroll(canEnroll);
        permission.setCanComment(canComment);
        abstractUserService.saveUser(user);
        return true;
    }
    @DeleteMapping("/event")
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @DeleteMapping("/eventComment")
    public boolean deleteEventComment(long commentId) {
        return eventCommentService.deleteEventComment(commentId);
    }

    @DeleteMapping("/replyComment")
    public boolean deleteReplyComment(long commentId) {
        return replyCommentService.deleteReplyComment(commentId);
    }
    @DeleteMapping("/post")
    public boolean deletePost(long postId) {
        return postService.deletePost(postId);
    }

}
