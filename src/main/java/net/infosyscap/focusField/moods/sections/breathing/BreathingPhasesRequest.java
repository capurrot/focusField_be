package net.infosyscap.focusField.moods.sections.breathing;

import lombok.Data;

@Data
public class BreathingPhasesRequest {
    private String phase;
    private String phaseLabel;
    private Integer duration;
    private String mode;
}