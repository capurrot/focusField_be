package net.infosyscap.focusField.moods.sections.journal;

import lombok.Data;

import java.util.List;

@Data
public class JournalRequest {
    private Boolean enabled;
    private String prompt;
    private String placeholder;
    private String save;
    private Boolean optional;

    private String goalLabel;
    private String how;
    private List<JournalGoalRequest> goals;
}

