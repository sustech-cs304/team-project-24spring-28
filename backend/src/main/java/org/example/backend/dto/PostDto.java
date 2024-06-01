package org.example.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.backend.domain.AbstractUser;
import org.example.backend.domain.Event;
import org.example.backend.domain.Post;
import org.example.backend.domain.User;

@Setter
@Getter
public class PostDto {
    private long postID;
    private String postLink;
    private String postTitle;
    private String postContent;
    private long postRelevantEventID;
    private int postLikeAmount;
    private int postCollectAmount;
    private int postCommentAmount;
    private String username;
    private String userBio;
    private String userAvatar;
    private String eventLink;
    private String eventName;
    private String eventIntroduction;
    private String eventPoster;
    private boolean likeOrNot;
    private boolean collectOrNot;

    public PostDto() {
    }

    public PostDto(Post post) {
        Event event = post.getEvent();
        AbstractUser user = post.getUser();
        this.postID = post.getId();
        this.postLink = String.valueOf(post.getId());
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.postRelevantEventID = event.getId();
        this.postLikeAmount = post.getLikeUsers().size();
        this.username = user.getUsername();
        this.userBio = user.getBio();
        this.userAvatar = user.getAvatar();
        this.eventLink = String.valueOf(event.getId());
        this.eventName = event.getName();
        this.eventIntroduction = event.getIntroduction();
        this.eventPoster = event.getPosterUrl();
    }
}
