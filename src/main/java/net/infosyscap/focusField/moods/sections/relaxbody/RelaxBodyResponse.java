package net.infosyscap.focusField.moods.sections.relaxbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelaxBodyResponse {
    private Long id;
    private Boolean enabled;
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
    private List<RelaxExerciseResponse> exercises;
}
