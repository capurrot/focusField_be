package net.infosyscap.focusField.moods.sections.environment;

import lombok.Data;
import java.util.List;

@Data
public class EnvironmentRequest {
    private Long id;
    private boolean enabled;

    private String backgroundImage;
    private String backgroundVideo;

    private List<String> soundscape;

    private String audioSrc;
    private String suggestion;
    private String duration;
    private String title;
    private String start;
    private String stop;
    private String mute;
    private String unmute;
    private String fullscreen;
    private String exitFullscreen;
    private String suggestedDuration;
}


