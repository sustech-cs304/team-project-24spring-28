package org.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class FormEnrollment extends AbstractEnrollment {
    @ManyToMany
    private List<User> participants;
    @OneToMany
    private List<DefinedFormEntry> definedFormEntries;

    public List<DefinedFormEntry> getDefinedFormEntries() {
        return definedFormEntries;
    }

    public void setDefinedFormEntries(List<DefinedFormEntry> definedFormEntries) {
        this.definedFormEntries = definedFormEntries;
    }


    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
