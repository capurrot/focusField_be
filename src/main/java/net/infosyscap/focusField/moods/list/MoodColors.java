package net.infosyscap.focusField.moods.list;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(exclude = "mood")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mood_colors")
public class MoodColors {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_list_colors_seq")
    @SequenceGenerator(name = "mood_list_colors_seq", sequenceName = "mood_list_colors_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mood_id")
    @JsonIgnore
    private MoodList mood;
}

