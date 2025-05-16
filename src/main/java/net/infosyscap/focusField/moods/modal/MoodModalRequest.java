package net.infosyscap.focusField.moods.modal;

import lombok.Data;
import net.infosyscap.focusField.moods.sections.cta.CtaRequest;

@Data
public class MoodModalRequest {
    private String loading;
    private String notFound;
    private InfoModalRequest infoModal;
    private CtaModalRequest ctaModal;
    private ModalTitleRequest title;
    private ModalDescRequest desc;
    private ModalSectionsRequest sections;
}

