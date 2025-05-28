package net.infosyscap.focusField.moods.sections.breathing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breathing-modes")
@RequiredArgsConstructor
public class BreathingModeController {

    private final BreathingModeRepository modeRepo;

    @PostMapping
    public ResponseEntity<BreathingMode> createMode(@RequestBody BreathingMode mode) {
        return ResponseEntity.ok(modeRepo.save(mode));
    }

    @GetMapping
    public ResponseEntity<List<BreathingMode>> getAllModes(@RequestParam(required = false) String lang) {
        if (lang != null && !lang.isEmpty()) {
            return ResponseEntity.ok(modeRepo.findByLang(lang));
        }
        return ResponseEntity.ok(modeRepo.findAll());
    }
}
