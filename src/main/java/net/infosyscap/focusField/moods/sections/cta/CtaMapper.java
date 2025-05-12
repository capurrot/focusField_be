package net.infosyscap.focusField.moods.sections.cta;

public class CtaMapper {

    public static Cta fromRequest(CtaRequest req) {
        if (req == null) return null;

        return Cta.builder()
                .id(req.getId())
                .text(req.getText())
                .build();
    }
}

