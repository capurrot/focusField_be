package net.infosyscap.focusField.moods.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CtaModal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cta_modal_seq")
    @SequenceGenerator(name = "cta_modal_seq", sequenceName = "cta_modal_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "cta_default")
    private String defaultText;
}

