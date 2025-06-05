package net.infosyscap.focusField.moods.sections.breathing;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreathingPhaseTemplateRepository extends JpaRepository<BreathingPhaseTemplate, Long> {
    List<BreathingPhaseTemplate> findByLang(String lang);
}