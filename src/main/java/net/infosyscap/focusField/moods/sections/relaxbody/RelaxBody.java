package net.infosyscap.focusField.moods.sections.relaxbody;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelaxBody {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relax_body_seq")
    @SequenceGenerator(name = "relax_body_seq", sequenceName = "relax_body_id_seq", allocationSize = 1)
    private Long id;

    private boolean enabled;
    private String title;

    @Column(length = 1000)
    private String description;

    private String start;
    private String stop;
    private String pause;
    private String pauseText;
    private String duration;
    private String scrollDown;
    private String scrollUp;
    private String completed;
    private String repeatIn;
    private int pauseDuration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RelaxExercise> exercises;
}