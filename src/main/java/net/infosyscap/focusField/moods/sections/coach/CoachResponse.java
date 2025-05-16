package net.infosyscap.focusField.moods.sections.coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachResponse {
    private Long id;
    private Boolean enabled;

    private String intro;
    private String obstacle;
    private String situation;
    private String feedback;
    private String next;
    private String finished;
    private String noSteps;

    private List<CoachStepResponse> steps;
}
