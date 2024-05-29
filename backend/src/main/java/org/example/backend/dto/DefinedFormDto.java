package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.domain.DefinedFormEntry;

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
}
