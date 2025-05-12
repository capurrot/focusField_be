package net.infosyscap.focusField.moods.sections.spiritual;

import net.infosyscap.focusField.moods.sections.spiritual.Spiritual;

public class SpiritualMapper {

    public static Spiritual fromRequest(SpiritualRequest req) {
        if (req == null) return null;

        return Spiritual.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .type(req.getType())
                .build();
    }
}

