package net.infosyscap.focusField.moods.sections.relaxbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelaxExerciseResponse {
    private Long id;
    private String name;
    private String instructions;
    private String duration;
    private String image;
}
