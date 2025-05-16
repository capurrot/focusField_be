package net.infosyscap.focusField.moods.sections.coach;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachStep {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_step_seq")
    @SequenceGenerator(name = "coach_step_seq", sequenceName = "coach_step_id_seq", allocationSize = 1)
    private Long id;

    private String situation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoachAnswer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;

}