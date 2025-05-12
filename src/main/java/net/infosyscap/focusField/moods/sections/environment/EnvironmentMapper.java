package net.infosyscap.focusField.moods.sections.environment;

import net.infosyscap.focusField.moods.sections.environment.Environment;

public class EnvironmentMapper {

    public static Environment fromRequest(EnvironmentRequest req) {
        if (req == null) return null;

        return Environment.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .backgroundImage(req.getBackgroundImage())
                .backgroundVideo(req.getBackgroundVideo())
                .soundscape(req.getSoundscape())
                .audioSrc(req.getAudioSrc())
                .suggestion(req.getSuggestion())
                .duration(req.getDuration())
                .title(req.getTitle())
                .start(req.getStart())
                .stop(req.getStop())
                .mute(req.getMute())
                .unmute(req.getUnmute())
                .fullscreen(req.getFullscreen())
                .exitFullscreen(req.getExitFullscreen())
                .suggestedDuration(req.getSuggestedDuration())
                .build();
    }

}

