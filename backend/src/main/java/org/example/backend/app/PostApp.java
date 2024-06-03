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

/**
 * 该类用于处理帖子相关的请求
 * @author Shinomiya
 * @version 1.0
 */
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

    /**
     * 发布帖子
     * @param token 用户token
     * @param postTitle 帖子标题
     * @param postContent 帖子内容
     * @param eventId 相关事件ID
     * @return 帖子ID
     */
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

    /**
     * 获取帖子
     * @param postId 帖子ID
     * @return 帖子信息
     */
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
        postDto.setUserID(user.getId());
        postDto.setUserBio(user.getBio());
        postDto.setUserAvatar(user.getAvatar());
        postDto.setLikeOrNot(post.getLikeUsers().contains(user));
        postDto.setCollectOrNot(((User) user).getFavouritePosts().contains(post));
        return postDto;
    }

    /**
     * @param token 用户token
     * @param postId 帖子ID
     * @return 点赞帖子是否成功
     */
    @PostMapping("/likeThePost")
    public boolean likeThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        AbstractUser user = JwtUtil.verifyToken(token);
        List<AbstractUser> likeUsers = post.getLikeUsers();
        if (likeUsers.contains(user)) {
            return true;
        }
        likeUsers.add(user);
        return postService.savePost(post);
    }

    /**
     * @param token 用户token
     * @param postId 帖子ID
     * @return 取消点赞帖子是否成功
     */
    @PostMapping("/dislikeThePost")
    public boolean dislikeThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        AbstractUser user = JwtUtil.verifyToken(token);
        List<AbstractUser> likeUsers = post.getLikeUsers();
        if (!likeUsers.contains(user)) {
            return false;
        }
        likeUsers.remove(user);
        return postService.savePost(post);
    }

    /**
     * @param token 用户token
     * @param postId 帖子ID
     * @return 收藏帖子是否成功
     */
    @PostMapping("/collectThePost")
    public boolean collectThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        AbstractUser user = JwtUtil.verifyToken(token);
        if (!(user instanceof User)) {
            throw new MyException(0, "Only user can collect post");
        }
        if (((User) user).getFavouritePosts().contains(post)) {
            return true;
        }
        ((User) user).getFavouritePosts().add(post);
        post.setPostCollectAmount(post.getPostCollectAmount() + 1);
        return abstractUserService.saveUser(user);
    }

    /**
     * @param token 用户token
     * @param postId 帖子ID
     * @return 取消收藏帖子是否成功
     */
    @PostMapping("/discollectThePost")
    public boolean discollectThePost(@RequestHeader("Authorization") String token, @RequestParam("postID") String postId) {
        Post post = postService.findPostById(Long.parseLong(postId));
        AbstractUser user = JwtUtil.verifyToken(token);
        if (!(user instanceof User)) {
            throw new MyException(1, "Only user can discollect post");
        }
        if (!((User) user).getFavouritePosts().contains(post)) {
            return false;
        }
        ((User) user).getFavouritePosts().remove(post);
        post.setPostCollectAmount(post.getPostCollectAmount() - 1);
        return abstractUserService.saveUser(user);
    }

    /**
     * @return 所有帖子
     */
    @GetMapping("/getPostSquare")
    public List<PostDto> getPostSquare() {
        List<Post> posts = postService.findAllPosts();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(constructPostDto(post));
        }
        return postDtos;
    }

    /**
     * @return 所有事件
     */
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

    /**
     * @param postId 帖子ID
     * @return 删除帖子是否成功
     */
    @DeleteMapping("/delete/{postID}")
    public boolean deletePost(@PathVariable("postID") String postId) {
        return postService.deletePostById(Long.parseLong(postId));
    }

    /**
     * @param token 用户token
     * @return 用户收藏的帖子
     */
    @GetMapping("/getPostSquare/collect")
    public List<PostDto> getPostSquareCollect(@RequestHeader("Authorization") String token) {
        AbstractUser user = JwtUtil.verifyToken(token);
        List<Post> posts = ((User) user).getFavouritePosts();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(constructPostDto(post));
        }
        return postDtos;
    }

    /**
     * @param token 用户token
     * @return 用户发布的帖子
     */
    @GetMapping("/getPostSquare/write")
    public List<PostDto> getPostSquareWrite(@RequestHeader("Authorization") String token) {
        AbstractUser user = JwtUtil.verifyToken(token);
        List<Post> posts = postService.findPostsByUserId(user.getId());
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(constructPostDto(post));
        }
        return postDtos;
    }

}
