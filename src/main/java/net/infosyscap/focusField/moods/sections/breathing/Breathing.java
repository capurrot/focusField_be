package net.infosyscap.focusField.moods.sections.breathing;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Breathing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breathing_seq")
    @SequenceGenerator(name = "breathing_seq", sequenceName = "breathing_id_seq", allocationSize = 1)
    private Long id;

    private Boolean enabled;
    private String techniqueLabel;
    private String technique;
    private String rounds;
    private String totalDurationLabel;
    private int totalDuration;
    private String scope;
    private String start;
    private String stop;

    @OneToMany(mappedBy = "breathing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BreathingPhases> phases;
}