package net.infosyscap.focusField.moods.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mood_modal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoodModal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_modal_seq")
    @SequenceGenerator(name = "mood_modal_seq", sequenceName = "mood_modal_id_seq", allocationSize = 1)
    private Long id;

    private String loading;
    private String notFound;

    @OneToOne(cascade = CascadeType.ALL)
    private InfoModal infoModal;

    @OneToOne(cascade = CascadeType.ALL)
    private CtaModal cta;

    @OneToOne(cascade = CascadeType.ALL)
    private ModalTitle title;

    @OneToOne(cascade = CascadeType.ALL)
    private ModalDesc desc;

    @OneToOne(cascade = CascadeType.ALL)
    private MoodSections sections;
}
