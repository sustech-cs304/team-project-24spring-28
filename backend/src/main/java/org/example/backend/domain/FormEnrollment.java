package org.example.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class FormEnrollment extends AbstractEnrollment {
    @OneToMany
    private List<DefinedFormEntry> definedFormEntries;
    @OneToMany(mappedBy = "formEnrollment")
    List<EnrollForm> enrollForms;

    public List<EnrollForm> getEnrollForms() {
        return enrollForms;
    }

    public void setEnrollForms(List<EnrollForm> enrollForms) {
        this.enrollForms = enrollForms;
    }

    public List<DefinedFormEntry> getDefinedFormEntries() {
        return definedFormEntries;
    }

    public void setDefinedFormEntries(List<DefinedFormEntry> definedFormEntries) {
        this.definedFormEntries = definedFormEntries;
    }
}
