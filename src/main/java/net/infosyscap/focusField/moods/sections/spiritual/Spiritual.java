package net.infosyscap.focusField.moods.sections.spiritual;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spiritual {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spiritual_seq")
    @SequenceGenerator(name = "spiritual_seq", sequenceName = "spiritual_id_seq", allocationSize = 1)
    private Long id;

    private Boolean enabled;
    private String type;
    private String verse;

    @Column(length = 1000)
    private String text;
}