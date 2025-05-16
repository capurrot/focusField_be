package net.infosyscap.focusField.moods.sections.breathing;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "breathing_phases")
public class BreathingPhases {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breathing_phase_seq")
    @SequenceGenerator(name = "breathing_phase_seq", sequenceName = "breathing_phase_id_seq", allocationSize = 1)
    private Long id;

    private String phase;
    private String phaseLabel;
    private int duration;
    private String mode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breathing_id")
    @JsonIgnore
    private Breathing breathing;
}