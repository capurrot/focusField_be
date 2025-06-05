package net.infosyscap.focusField.moods.sections.breathing;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreathingModeRepository extends JpaRepository<BreathingMode, Long> {
    List<BreathingMode> findByLang(String lang);
}