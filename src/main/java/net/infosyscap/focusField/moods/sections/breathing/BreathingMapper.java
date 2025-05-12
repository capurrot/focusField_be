package net.infosyscap.focusField.moods.sections.breathing;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BreathingMapper {

    public static Breathing fromRequest(BreathingRequest req) {
        if (req == null) return null;

        return Breathing.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .techniqueLabel(req.getTechniqueLabel())
                .technique(req.getTechnique())
                .inBreath(req.getInBreath())
                .hold(req.getHold())
                .outBreath(req.getOutBreath())
                .rounds(req.getRounds())
                .durationLabel(req.getDurationLabel())
                .duration(req.getDuration())
                .scope(req.getScope())
                .start(req.getStart())
                .stop(req.getStop())
                .instructions(Optional.ofNullable(req.getInstructions()).orElse(List.of()))
                .phases(Optional.ofNullable(req.getPhases()).orElse(Map.of()))
                .build();
    }
}

