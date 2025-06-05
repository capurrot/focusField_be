package net.infosyscap.focusField.moods.sections.coach;

import lombok.Data;
import java.util.List;

@Data
public class CoachRequest {
    private Boolean enabled;

    private String intro;
    private String obstacle;
    private String situation;
    private String feedback;
    private String next;
    private String finished;
    private String noSteps;

    private List<CoachStepRequest> steps;
}


