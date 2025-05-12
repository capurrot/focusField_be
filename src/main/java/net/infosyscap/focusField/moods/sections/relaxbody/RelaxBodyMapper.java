package net.infosyscap.focusField.moods.sections.relaxbody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RelaxBodyMapper {

    public static RelaxBody fromRequest(RelaxBodyRequest req) {
        if (req == null) return null;

        return RelaxBody.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .title(req.getTitle())
                .description(req.getDescription())
                .start(req.getStart())
                .stop(req.getStop())
                .pause(req.getPause())
                .pauseText(req.getPauseText())
                .duration(req.getDuration())
                .scrollDown(req.getScrollDown())
                .scrollUp(req.getScrollUp())
                .completed(req.getCompleted())
                .repeatIn(req.getRepeatIn())
                .pauseDuration(req.getPauseDuration())
                .exercises(mapExercises(req.getExercises()))
                .build();
    }

    private static List<RelaxExercise> mapExercises(List<RelaxExerciseRequest> reqList) {
        if (reqList == null) return List.of();

        return reqList.stream()
                .map(r -> RelaxExercise.builder()
                        .id(r.getId())
                        .name(r.getName())
                        .instructions(r.getInstructions())
                        .duration(r.getDuration())
                        .image(r.getImage())
                        .build())
                .collect(Collectors.toList());
    }
}

