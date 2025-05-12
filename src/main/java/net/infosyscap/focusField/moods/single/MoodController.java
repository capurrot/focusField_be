package net.infosyscap.focusField.moods.single;

import lombok.RequiredArgsConstructor;
import net.infosyscap.focusField.moods.list.MoodListRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/focus-field/mood")
@RequiredArgsConstructor
public class MoodController {

    private final MoodService moodService;
    private final MoodListRepository moodListRepository;

    @PostMapping
    public ResponseEntity<Mood> createMood(@RequestBody MoodRequest moodRequest) {
        Mood mood = MoodMapper.fromRequest(moodRequest, moodListRepository);
        Mood savedMood = moodService.saveMood(mood);
        return ResponseEntity.ok(savedMood);
    }

    @GetMapping("/{slug}/{lang}")
    public ResponseEntity<Mood> getMoodBySlugAndLang(@PathVariable String slug, @PathVariable String lang) {
        Mood mood = moodService.getMoodBySlugAndLang(slug, lang);
        return ResponseEntity.ok(mood);
    }
}
