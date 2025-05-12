package net.infosyscap.focusField.moods.list;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mood_list")
public class MoodList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_list_seq")
    @SequenceGenerator(name = "mood_list_seq", sequenceName = "mood_list_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String background;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoodTags> tag;

    @OneToMany(mappedBy = "mood", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoodColors> colors;

    @Column(nullable = false)
    private String opacity;

    @Column(nullable = false)
    private String icon;
}
