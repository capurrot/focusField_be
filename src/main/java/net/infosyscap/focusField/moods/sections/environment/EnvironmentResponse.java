package net.infosyscap.focusField.moods.sections.environment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentResponse {
    private Long id;
    private Boolean enabled;

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
