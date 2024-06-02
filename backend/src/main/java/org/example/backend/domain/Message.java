package org.example.backend.domain;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractUser to;

    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractUser from;

    private boolean read;

    private LocalDateTime time;

    @Column(columnDefinition = "text")
    private String content;
    public Message() {
    }
    public Message(String type, AbstractUser to, AbstractUser from, boolean read, LocalDateTime time, String content) {
        this.type = type;
        this.to = to;
        this.from = from;
        this.read = read;
        this.time = time;
        this.content = content;
    }

}
