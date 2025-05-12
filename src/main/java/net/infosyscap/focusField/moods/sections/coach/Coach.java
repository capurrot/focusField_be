package net.infosyscap.focusField.moods.sections.coach;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_seq")
    @SequenceGenerator(name = "coach_seq", sequenceName = "coach_id_seq", allocationSize = 1)
    private Long id;

    private boolean enabled;

    @Column(length = 1000)
    private String intro;

    private String obstacle;
    private String situation;
    private String feedback;
    private String next;

    @Column(length = 1000)
    private String finished;
    private String noSteps;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CoachStep> steps;
}