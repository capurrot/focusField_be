package net.infosyscap.focusField.moods.sections.breathing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus-field/breathing-phases")
@RequiredArgsConstructor
public class BreathingPhasesController {

    private final BreathingPhasesRepository breathingPhasesRepository;

    @GetMapping
    public List<BreathingPhases> getAllBreathingPhases() {
        return breathingPhasesRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BreathingPhases saveBreathingPhases(@RequestBody BreathingPhases breathingPhases) {
        return breathingPhasesRepository.save(breathingPhases);
    }
}
