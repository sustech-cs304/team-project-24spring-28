package org.example.backend.domain;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class User extends AbstractUser {
    private List<Event> favouriteEvents;
}
