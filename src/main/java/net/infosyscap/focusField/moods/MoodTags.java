package net.infosyscap.focusField.moods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(exclude = "mood")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mood_tags")
public class MoodTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mood_id")
    @JsonIgnore
    private MoodList mood;
}
