package net.infosyscap.focusField.moods.sections.coach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoachAnswerResponse {
    private Long id;
    private String text;
    private Boolean correct;
    private String feedback;
}
