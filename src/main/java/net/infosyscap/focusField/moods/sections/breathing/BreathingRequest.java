package net.infosyscap.focusField.moods.sections.breathing;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class BreathingRequest {
    private Long id;
    private boolean enabled;
    private String techniqueLabel;
    private String technique;
    private String inBreath;
    private String hold;
    private String outBreath;
    private String rounds;
    private String durationLabel;
    private int duration;
    private String scope;
    private String start;
    private String stop;
    private List<String> instructions;

    private Map<String, Integer> phases;
}
