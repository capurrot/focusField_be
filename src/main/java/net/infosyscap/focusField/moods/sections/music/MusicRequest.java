package net.infosyscap.focusField.moods.sections.music;

import lombok.Data;
import java.util.List;

@Data
public class MusicRequest {
    private Long id;
    private String title;
    private String playlistUrl;
    private List<String> tags;
    private String scope;
    private String audius;
}
