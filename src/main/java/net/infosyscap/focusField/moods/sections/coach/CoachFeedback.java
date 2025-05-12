package net.infosyscap.focusField.moods.sections.coach;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachFeedback {
    @Column(length = 1000)
    private String correct;

    @Column(length = 1000)
    private String wrong;
}