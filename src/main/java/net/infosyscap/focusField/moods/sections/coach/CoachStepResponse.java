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
public class CoachStepResponse {
    private Long id;
    private String situation;
    private List<CoachAnswerResponse> answers;
}
