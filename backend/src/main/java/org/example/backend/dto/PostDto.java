package org.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDto {
    private long postID;
    private String postLink;
    private String postTitle;
    private String postContent;
    private long postRelevantEventID;
    private long username;
    private String userBio;
    private String userAvatar;
}
