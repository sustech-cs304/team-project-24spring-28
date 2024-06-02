package org.example.backend.app;


import org.example.backend.config.MyException;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.Post;
import org.example.backend.dto.AbstractUserDto;
import org.example.backend.dto.PostDto;
import org.example.backend.service.AbstractUserService;
import org.example.backend.service.EventCommentService;
import org.example.backend.service.PostService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileApp {
    @Autowired
    private AbstractUserService abstractUserService;
    @Autowired
    private PostService postService;
    @Autowired
    private EventCommentService eventCommentService;

    @PostMapping("/info/edit")
    public boolean editProfile(@RequestHeader("Authorization") String token, @RequestParam("name") String name, @RequestParam("bio") String bio){
        AbstractUser user = JwtUtil.verifyToken(token);
        if(user == null){
            throw new MyException(0, "Invalid token");
        }
        user.setName(name);
        user.setBio(bio);
        abstractUserService.saveUser(user);
        return true;
    }

    @PostMapping("/avatar/edit")
    public boolean editAvatar(@RequestHeader("Authorization") String token, @RequestParam("avatar") String avatar){
        AbstractUser user = JwtUtil.verifyToken(token);
        if(user == null){
            throw new MyException(0, "Invalid token");
        }
        user.setAvatar(avatar);
        abstractUserService.saveUser(user);
        return true;
    }

    @GetMapping("/info/get")
    public AbstractUserDto getProfile(@RequestParam("userID") long userID){
        AbstractUser user = abstractUserService.findUserById(userID);
        if(user == null){
            throw new MyException(0, "User not found");
        }
        AbstractUserDto abstractUserDto = new AbstractUserDto();
        abstractUserDto.setId(user.getId());
        abstractUserDto.setName(user.getName());
        abstractUserDto.setBio(user.getBio());
        abstractUserDto.setAvatar(user.getAvatar());
        return abstractUserDto;
    }

    @GetMapping("/profile/post")
    public List<PostDto> getProfilePost(@RequestParam("userID") long userID){
        AbstractUser user = abstractUserService.findUserById(userID);
        if(user == null){
            throw new MyException(0, "User not found");
        }
        List<PostDto> postDtoList = new ArrayList<>();
        for(Post post : postService.findPostsByUserId(userID)){
            PostDto postDto = new PostDto(post);
            postDto.setPostCollectAmount(post.getPostCollectAmount());
            postDto.setPostCommentAmount(eventCommentService.findEventCommentByPostId(post.getId()).size());
            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}
