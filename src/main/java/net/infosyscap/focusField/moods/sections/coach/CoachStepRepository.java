package net.infosyscap.focusField.moods.sections.coach;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoachStepRepository extends JpaRepository<CoachStep, Long> {
    Optional<CoachStep> findBySituation(String situation);
}