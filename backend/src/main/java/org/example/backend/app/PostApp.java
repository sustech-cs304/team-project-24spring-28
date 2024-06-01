package org.example.backend.app;

import org.example.backend.config.MyException;
import org.example.backend.domain.*;
import org.example.backend.dto.EventDto;
import org.example.backend.dto.PostDto;
import org.example.backend.service.AbstractUserService;
import org.example.backend.service.EventCommentService;
import org.example.backend.service.EventService;
import org.example.backend.service.PostService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostApp {
    @Autowired
    private PostService postService;
    @Autowired
    private AbstractUserService abstractUserService;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventCommentService eventCommentService;

    @PostMapping("/releasePost")
    public Long releasePost(@RequestHeader("Authorization") String token, @RequestParam("postTitle") String postTitle, @RequestParam("postContent") String postContent, @RequestParam("postRelevantEvent") String eventId) {
//        long userId = JwtUtil.getIdByToken(token);
        AbstractUser user = JwtUtil.verifyToken(token);
        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setUser(user);
        post.setEvent(eventService.findEventById(Long.parseLong(eventId)));
        postService.savePost(post);
        return post.getId();
    }

    @GetMapping("/getFullPost")
    public PostDto getFullPost(@RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        PostDto postDto = constructPostDto(post);
        postDto.setLikeOrNot(post.getLikeUsers().contains(post.getUser()));
        postDto.setCollectOrNot(((User) post.getUser()).getFavouritePosts().contains(post));
        return postDto;
    }

    private PostDto constructPostDto(Post post) {
        AbstractUser user = post.getUser();
        PostDto postDto = new PostDto();
        postDto.setPostID(post.getId());
        postDto.setPostTitle(post.getPostTitle());
        postDto.setPostContent(post.getPostContent());
        postDto.setPostLink(String.valueOf(post.getId()));
        postDto.setPostRelevantEventID(post.getEvent().getId());
        postDto.setPostLikeAmount(post.getLikeUsers().size());
        postDto.setPostCollectAmount(post.getPostCollectAmount());
        postDto.setPostCommentAmount(eventCommentService.findEventCommentByPostId(post.getId()).size());
        postDto.setUsername(user.getUsername());
        postDto.setUserBio(user.getBio());
        postDto.setUserAvatar(user.getAvatar());
        postDto.setLikeOrNot(post.getLikeUsers().contains(user));
        postDto.setCollectOrNot(((User) user).getFavouritePosts().contains(post));
        return postDto;
    }

    @PostMapping("/likeThePost")
    public boolean likeThePost(@RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        List<AbstractUser> likeUsers = post.getLikeUsers();
        if (likeUsers.contains(post.getUser())) {
            return true;
        }
        likeUsers.add(post.getUser());
        return postService.savePost(post);
    }

    @PostMapping("/dislikeThePost")
    public boolean dislikeThePost(@RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        List<AbstractUser> likeUsers = post.getLikeUsers();
        if (!likeUsers.contains(post.getUser())) {
            return false;
        }
        likeUsers.remove(post.getUser());
        return postService.savePost(post);
    }

    @PostMapping("/collectThePost")
    public boolean collectThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        long userId = JwtUtil.getIdByToken(token);
        AbstractUser user = abstractUserService.findUserById(userId);
        if (!(user instanceof User)) {
            throw new MyException(0, "Only user can collect post");
        }
        ((User) user).getFavouritePosts().add(post);
        post.setPostCollectAmount(post.getPostCollectAmount() + 1);
        return abstractUserService.saveUser(user);
    }

    @PostMapping("/discollectThePost")
    public boolean discollectThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        long userId = JwtUtil.getIdByToken(token);
        AbstractUser user = abstractUserService.findUserById(userId);
        if (!(user instanceof User)) {
            throw new MyException(1, "Only user can discollect post");
        }
        ((User) user).getFavouritePosts().remove(post);
        post.setPostCollectAmount(post.getPostCollectAmount() - 1);
        return abstractUserService.saveUser(user);
    }

    @GetMapping("/getPostSquare")
    public List<PostDto> getPostSquare() {
        List<Post> posts = postService.findAllPosts();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(constructPostDto(post));
        }
        return postDtos;
    }

    @GetMapping("/getAllEvent")
    public List<EventDto> getAllEvent() {
        List<EventDto> eventDtos = new ArrayList<>();
        List<Event> events = eventService.findAllEvents();
        for (Event event : events) {
            eventDtos.add(constructEventDto(event));
        }
        return eventDtos;
    }

    private EventDto constructEventDto(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setEventName(event.getName());
        return eventDto;
    }

    @DeleteMapping("/delete/{postID}")
    public boolean deletePost(@PathVariable("postID") String postId) {
        return postService.deletePostById(Long.parseLong(postId));
    }
}
