package net.infosyscap.focusField.moods.sections.breathing;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class BreathingPhasesService {
    private final BreathingPhasesRepository breathingPhasesRepository;

    public BreathingPhases saveBreathingPhases(BreathingPhases breathingPhases) {
        return breathingPhasesRepository.save(breathingPhases);
    }
}
