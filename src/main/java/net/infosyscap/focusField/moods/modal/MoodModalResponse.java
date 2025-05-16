package net.infosyscap.focusField.moods.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoodModalResponse {
    private Long id;
    private String loading;
    private String notFound;
    private InfoModalResponse infoModal;
    private CtaModalResponse ctaModal;
    private ModalTitleResponse title;
    private ModalDescResponse desc;
    private ModalSectionsResponse sections;
}
