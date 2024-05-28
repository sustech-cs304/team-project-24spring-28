package org.example.backend.domain;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String postLink;

    private String postTitle;

    private String postContent;

    @ManyToOne
    private Event event;

    @ManyToOne
    private AbstractUser user;

    private int likes;

}
