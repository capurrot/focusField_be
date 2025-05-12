package net.infosyscap.focusField.moods.sections.coach;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CoachMapper {

    public static Coach fromRequest(CoachRequest req) {
        if (req == null) return null;

        return Coach.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .intro(req.getIntro())
                .obstacle(req.getObstacle())
                .situation(req.getSituation())
                .feedback(req.getFeedback())
                .next(req.getNext())
                .finished(req.getFinished())
                .noSteps(req.getNoSteps())
                .steps(mapSteps(req.getSteps()))
                .build();
    }

    private static List<CoachStep> mapSteps(List<CoachStepRequest> reqList) {
        if (reqList == null) return List.of();

        return reqList.stream()
                .map(stepReq -> CoachStep.builder()
                        .id(stepReq.getId())
                        .situation(stepReq.getSituation())
                        .answers(mapAnswers(stepReq.getAnswers()))
                        .build())
                .collect(Collectors.toList());
    }

    private static List<CoachAnswer> mapAnswers(List<CoachAnswerRequest> reqList) {
        if (reqList == null) return List.of();

        return reqList.stream()
                .map(answerReq -> CoachAnswer.builder()
                        .text(answerReq.getText())
                        .correct(answerReq.isCorrect())
                        .feedback(answerReq.getFeedback())
                        .build())
                .collect(Collectors.toList());
    }
}


