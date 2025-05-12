package net.infosyscap.focusField.moods.sections.cta;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cta_seq")
    @SequenceGenerator(name = "cta_seq", sequenceName = "cta_id_seq", allocationSize = 1)
    private Long id;

    private String text;
    private String action;
}