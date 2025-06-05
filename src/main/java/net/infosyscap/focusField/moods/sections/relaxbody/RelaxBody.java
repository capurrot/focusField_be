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

    private Boolean enabled;

    private String title;

    @Column(length = 1000)
    private String description;

    private String start;

    private String stop;

    private String pause;

    @Column(name = "pause_text")
    private String pauseText;

    private String duration;

    @Column(name = "scroll_down")
    private String scrollDown;

    @Column(name = "scroll_up")
    private String scrollUp;

    private String completed;

    @Column(name = "repeat_in")
    private String repeatIn;

    @Column(name = "pause_duration")
    private int pauseDuration;

    @OneToMany(mappedBy = "relaxBody", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelaxExercise> exercises;
}
