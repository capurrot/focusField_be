package net.infosyscap.focusField.moods.sections.coach;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoachOption {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_option_seq")
    @SequenceGenerator(name = "coach_option_seq", sequenceName = "coach_option_id_seq", allocationSize = 1)
    private Long id;

    private String text;
    private boolean correct;
}