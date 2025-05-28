package net.infosyscap.focusField.moods.sections.breathing;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "breathing_modes")
public class BreathingMode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breathing_mode_seq")
    @SequenceGenerator(name = "breathing_mode_seq", sequenceName = "breathing_mode_id_seq", allocationSize = 1)
    private Long id;

    private String name; // Es. "Profondamente", "Lentamente"

    @OneToMany(mappedBy = "breathingMode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreathingPhaseTemplate> phases;
}
