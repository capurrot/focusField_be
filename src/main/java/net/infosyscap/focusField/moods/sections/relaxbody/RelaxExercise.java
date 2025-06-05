package net.infosyscap.focusField.moods.sections.relaxbody;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelaxExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relax_exercise_seq")
    @SequenceGenerator(name = "relax_exercise_seq", sequenceName = "relax_exercise_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String instructions;

    private String duration;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relax_body_id")
    private RelaxBody relaxBody;
}
