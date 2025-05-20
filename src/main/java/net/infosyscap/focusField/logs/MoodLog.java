package net.infosyscap.focusField.logs;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mood_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_log")
    @SequenceGenerator(name = "mood_log", sequenceName = "mood_id_log", allocationSize = 1)
    private Long id;

    private Long userId;
    private String moodSlug;
    private String language;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationSeconds;
    private Boolean completed;
}
