package net.infosyscap.focusField.moods.sections.breathing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BreathingResponse {
    private Long id;
    private Boolean enabled;
    private String techniqueLabel;
    private String technique;
    private String rounds;
    private String totalDurationLabel;
    private int totalDuration;
    private String scope;
    private String start;
    private String stop;

    private List<BreathingPhasesResponse> phases;
}
