package org.example.backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User extends AbstractUser {
    @ManyToMany
    private List<Event> favouriteEvents;
}
