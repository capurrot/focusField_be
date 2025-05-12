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

    private boolean enabled;
    private String techniqueLabel;
    private String technique;
    private String inBreath;
    private String hold;
    private String outBreath;
    private String rounds;
    private String durationLabel;
    private int duration;
    private String scope;
    private String start;
    private String stop;


    @ElementCollection
    private List<String> instructions;

    @ElementCollection
    private Map<String, Integer> phases;
}