package net.infosyscap.focusField.moods.sections.coach;

import lombok.Data;

@Data
public class CoachAnswerRequest {
    private String text;
    private Boolean correct;
    private String feedback;
}

