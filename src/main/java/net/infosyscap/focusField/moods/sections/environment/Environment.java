package net.infosyscap.focusField.moods.sections.environment;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environment_seq")
    @SequenceGenerator(name = "environment_seq", sequenceName = "environment_id_seq", allocationSize = 1)
    private Long id;

    private boolean enabled;

    private String backgroundImage;
    private String backgroundVideo;

    @ElementCollection
    private List<String> soundscape;

    private String audioSrc;

    @Column(length = 1000)
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