package org.example.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String postTitle;

    private String postContent;

    @ManyToOne
    private Event event;

    @ManyToOne
    private AbstractUser user;

    private int likes;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Post) {
            return this.id == ((Post) obj).getId();
        } else {
            return false;
        }
    }
}
