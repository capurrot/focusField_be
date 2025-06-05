package net.infosyscap.focusField.moods.sections.breathing;

import lombok.Data;
import java.util.List;

@Data
public class BreathingRequest {
    private Boolean enabled;
    private String techniqueLabel;
    private String technique;
    private String rounds;
    private String totalDurationLabel;
    private int totalDuration;
    private String scope;
    private String start;
    private String stop;

    private List<BreathingPhasesRequest> phases;
}
