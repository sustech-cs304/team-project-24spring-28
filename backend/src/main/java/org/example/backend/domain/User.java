package org.example.backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@DiscriminatorValue(value = "User")
public class User extends AbstractUser {
    @ManyToMany
    private List<Event> favouriteEvents;

    @ManyToMany
    private List<Post> favouritePosts;

    @ManyToMany
    @JoinTable(name = "score",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> scoredEvents;

    public List<Event> getScoredEvents() {
        return scoredEvents;
    }

    public void setScoredEvents(List<Event> scoredEvents) {
        this.scoredEvents = scoredEvents;
    }

    public List<Event> getFavouriteEvents() {
        return favouriteEvents;
    }

    public void setFavouriteEvents(List<Event> favouriteEvents) {
        this.favouriteEvents = favouriteEvents;
    }

    public List<Post> getFavouritePosts() {
        return favouritePosts;
    }

    public void setFavouritePosts(List<Post> favouritePosts) {
        this.favouritePosts = favouritePosts;
    }
}
