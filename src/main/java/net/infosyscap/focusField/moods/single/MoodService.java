package net.infosyscap.focusField.moods.single;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoodService {

    private final MoodRepository moodRepository;

    public Mood saveMood(Mood mood) {
        return moodRepository.save(mood);
    }
    public Mood getMoodBySlugAndLang(String slug, String lang) {
        return moodRepository.findBySlugAndLang(slug, lang)
                .orElseThrow(() -> new RuntimeException("Mood not found"));
    }
}