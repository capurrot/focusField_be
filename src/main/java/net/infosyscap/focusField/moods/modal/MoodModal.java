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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "info_modal_id")
    private InfoModal infoModal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cta_modal_id")
    private CtaModal ctaModal;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "modal_title_id")
    private ModalTitle title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "modal_desc_id")
    private ModalDesc desc;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "mood_sections_id")
    private MoodSections sections;
}