package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.domain.DefinedFormEntry;

import javax.swing.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefinedFormDto {
    private long entryId;
    private String name;
    private String type;
    private boolean required;

    public DefinedFormDto(DefinedFormEntry definedFormEntry) {
        this.entryId = definedFormEntry.getEntryId();
        this.name = definedFormEntry.getName();
        this.type = definedFormEntry.getType();
        this.required = definedFormEntry.isRequired();
    }

    public DefinedFormEntry toDefinedFormEntry() {
        DefinedFormEntry definedFormEntry = new DefinedFormEntry();
        definedFormEntry.setEntryId(this.entryId);
        definedFormEntry.setName(this.name);
        definedFormEntry.setType(this.type);
        definedFormEntry.setRequired(this.required);
        return definedFormEntry;
    }

}
