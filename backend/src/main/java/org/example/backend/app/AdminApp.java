package org.example.backend.app;

import org.example.backend.config.MyException;
import org.example.backend.domain.Permission;
import org.example.backend.domain.User;
import org.example.backend.dto.UserDto;
import org.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 该类用于处理管理员相关的请求
 * @author bgamard
 * @version 1.0
 */

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

    /**
     * @return 返回所有用户的信息
     */
    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return abstractUserService.findAllUsers().stream().map(UserDto::new).toList();
    }

    /**
     * @param userId 用户id
     * @return 返回一个boolean值表示权限是否修改成功
     */
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

    /**
     * @return 返回一个boolean值表示活动是否删除成功
     */
    @DeleteMapping("/event")
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    /**
     * @return 返回一个boolean值表示评论是否删除成功
     */
    @DeleteMapping("/eventComment")
    public boolean deleteEventComment(long commentId) {
        return eventCommentService.deleteEventComment(commentId);
    }

    /**
     * @return 返回一个boolean值表示回复评论是否删除成功
     */
    @DeleteMapping("/replyComment")
    public boolean deleteReplyComment(long commentId) {
        return replyCommentService.deleteReplyComment(commentId);
    }

    /**
     * @return 返回一个boolean值表示帖子是否删除成功
     */
    @DeleteMapping("/post")
    public boolean deletePost(long postId) {
        return postService.deletePost(postId);
    }

}
