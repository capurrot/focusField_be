package net.infosyscap.focusField.moods.list;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_list_tags_seq")
    @SequenceGenerator(name = "mood_list_tags_seq", sequenceName = "mood_list_tags_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mood_id")
    @JsonIgnore
    private MoodList mood;
}
