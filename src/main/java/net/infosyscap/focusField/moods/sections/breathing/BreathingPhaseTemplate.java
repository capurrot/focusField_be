package net.infosyscap.focusField.moods.sections.breathing;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "breathing_phase_templates")
public class BreathingPhaseTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breathing_phase_template_seq")
    @SequenceGenerator(name = "breathing_phase_template_seq", sequenceName = "breathing_phase_template_id_seq", allocationSize = 1)
    private Long id;

    private String phase;       // es. "Inspira"
    private String phaseLabel;  // es. "Inspira a fondo"
    private String lang;
}
