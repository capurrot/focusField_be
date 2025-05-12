package net.infosyscap.focusField.moods.sections.relaxbody;

import lombok.Data;
import java.util.List;

@Data
public class RelaxBodyRequest {
    private Long id;
    private boolean enabled;
    private String title;
    private String description;
    private String start;
    private String stop;
    private String pause;
    private String pauseText;
    private String duration;
    private String scrollDown;
    private String scrollUp;
    private String completed;
    private String repeatIn;
    private int pauseDuration;
    private List<RelaxExerciseRequest> exercises;
}

