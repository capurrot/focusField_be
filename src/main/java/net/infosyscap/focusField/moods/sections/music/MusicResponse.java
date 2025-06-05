package net.infosyscap.focusField.moods.sections.music;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicResponse {
    private Long id;
    private String title;
    private String playlistUrl;
    private List<String> tags;
    private String scope;
    private String audius;
}
