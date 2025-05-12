package net.infosyscap.focusField.moods.modal;

import lombok.Data;
import net.infosyscap.focusField.moods.sections.cta.CtaRequest;

@Data
public class MoodModalRequest {
    private Long id;
    private String loading;
    private String notFound;
    private InfoModalRequest infoModal;
    private CtaRequest cta;
    private ModalTitleRequest title;
    private ModalDescRequest desc;
    private ModalSectionsRequest sections;
}

