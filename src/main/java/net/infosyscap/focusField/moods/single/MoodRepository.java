package net.infosyscap.focusField.moods.single;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MoodRepository extends JpaRepository<Mood, Long> {
    Optional<Mood> findBySlugAndLang(String slug, String lang);
}