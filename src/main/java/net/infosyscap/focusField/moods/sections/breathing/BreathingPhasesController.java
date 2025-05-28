package net.infosyscap.focusField.moods.sections.breathing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus-field/breathing-phases")
@RequiredArgsConstructor
public class BreathingPhasesController {

    private final BreathingPhaseRepository breathingPhaseTemplateRepository;

    @GetMapping
    public List<BreathingPhaseTemplate> getAllBreathingPhaseTemplates() {
        return breathingPhaseTemplateRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BreathingPhaseTemplate saveBreathingPhaseTemplate(@RequestBody BreathingPhaseTemplate template) {
        return breathingPhaseTemplateRepository.save(template);
    }
}

