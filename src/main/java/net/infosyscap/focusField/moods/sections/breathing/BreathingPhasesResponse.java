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
public class BreathingPhasesResponse {
    private Long id;
    private String phase;
    private String phaseLabel;
    private Integer duration;
    private String mode;
}
