package net.infosyscap.focusField.moods.sections.breathing;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreathingPhases {
    private int inPhase;
    private int holdPhase;
    private int outPhase;
}