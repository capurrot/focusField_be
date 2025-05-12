package net.infosyscap.focusField.moods.modal;

import net.infosyscap.focusField.moods.sections.cta.CtaRequest;

public class CtaModalMapper {
    public static CtaModal fromRequest(CtaRequest req) {
        if (req == null) return null;

        return CtaModal.builder()
                .id(req.getId())
                .defaultText(req.getText())
                .build();
    }
}
