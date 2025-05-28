package net.infosyscap.focusField.moods.sections.breathing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus-field/breathing-phases")
@RequiredArgsConstructor
public class BreathingPhaseTemplateController {

    private final BreathingPhaseTemplateRepository breathingPhaseTemplateRepository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<BreathingPhaseTemplate> getAll(@RequestParam(required = false) String lang) {
        if (lang != null) {
            return breathingPhaseTemplateRepository.findByLang(lang);
        }
        return breathingPhaseTemplateRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BreathingPhaseTemplate create(@RequestBody BreathingPhaseTemplate breathingPhaseTemplate) {
        return breathingPhaseTemplateRepository.save(breathingPhaseTemplate);
    }
}


