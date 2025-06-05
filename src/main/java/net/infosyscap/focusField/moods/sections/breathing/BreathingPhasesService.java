package net.infosyscap.focusField.moods.sections.breathing;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreathingPhasesService {

    private final BreathingPhaseTemplateRepository breathingPhaseTemplateRepository;

    public BreathingPhaseTemplate saveBreathingPhase(BreathingPhaseTemplate breathingPhase) {
        return breathingPhaseTemplateRepository.save(breathingPhase);
    }
}

