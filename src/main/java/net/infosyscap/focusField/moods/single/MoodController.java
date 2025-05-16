package net.infosyscap.focusField.moods.single;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/focus-field/mood")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;

    @GetMapping("/{slug}/{lang}")
    public ResponseEntity<MoodResponse> getMoodBySlugAndLang(@PathVariable String slug, @PathVariable String lang) {
        Mood mood = moodService.getMoodBySlugAndLang(slug, lang);
        MoodResponse response = MoodMapper.toResponse(mood);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public MoodResponse saveMood(@RequestBody MoodRequest moodRequest) {
        Mood savedMood = moodService.saveMood(moodRequest);
        return MoodMapper.toResponse(savedMood);
    }

    @PutMapping("/{slug}/{lang}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Mood updateMood(@PathVariable String slug, @PathVariable String lang, @RequestBody MoodRequest moodRequest) {
        return moodService.updateMood(slug, lang, moodRequest);
    }

}

