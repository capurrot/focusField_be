package net.infosyscap.focusField.moods.sections.coach;

import lombok.Data;
import java.util.List;

@Data
public class CoachStepRequest {
    private String situation;
    private List<CoachAnswerRequest> answers;
}


