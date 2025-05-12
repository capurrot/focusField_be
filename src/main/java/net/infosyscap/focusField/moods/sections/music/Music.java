package net.infosyscap.focusField.moods.sections.music;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "music_seq")
    @SequenceGenerator(name = "music_seq", sequenceName = "music_id_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String playlistUrl;

    @ElementCollection
    private List<String> tags;

    private String scope;
    private String audius;
}