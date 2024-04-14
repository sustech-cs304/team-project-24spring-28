package org.example.backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User extends AbstractUser {
    @ManyToMany
    private List<Event> favouriteEvents;

    public List<Event> getFavouriteEvents() {
        return favouriteEvents;
    }

    public void setFavouriteEvents(List<Event> favouriteEvents) {
        this.favouriteEvents = favouriteEvents;
    }
}
