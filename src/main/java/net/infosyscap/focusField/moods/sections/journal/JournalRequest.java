package net.infosyscap.focusField.moods.sections.journal;

import lombok.Data;

@Data
public class JournalRequest {
    private Boolean enabled;
    private String prompt;
    private String placeholder;
    private String save;
    private Boolean optional;
}

