package net.infosyscap.focusField.moods.sections.coach;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoachStepService {

    private final CoachStepRepository coachStepRepository;


    // Metodo per salvare o aggiornare un CoachStep
    public CoachStep saveCoachStep(CoachStep coachStep) {
        return coachStepRepository.save(coachStep);
    }

 public CoachStep  getCoachStepBySituation(String situation) {
        return coachStepRepository.findBySituation(situation)
                .orElseThrow(() -> new RuntimeException("CoachStep not found"));
    }
}
