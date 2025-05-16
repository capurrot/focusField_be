package net.infosyscap.focusField.moods.sections.coach;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_answer_seq")
    @SequenceGenerator(name = "coach_answer_seq", sequenceName = "coach_answer_id_seq", allocationSize = 1)
    private Long id;

    private String text;
    private Boolean correct;
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id")
    private CoachStep step;
}
