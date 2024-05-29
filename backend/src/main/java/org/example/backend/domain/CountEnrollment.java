package org.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class CountEnrollment extends AbstractEnrollment {

    private long capacity;
    private long count;
    @ManyToMany
    private List<User> participants;

    public long getCapacity() {
        return capacity;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
