package org.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class NormalEnrollment extends AbstractEnrollment {
    @ManyToMany
    private List<User> participants;
}
