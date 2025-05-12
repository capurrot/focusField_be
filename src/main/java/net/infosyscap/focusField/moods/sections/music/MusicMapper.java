package net.infosyscap.focusField.moods.sections.music;

import net.infosyscap.focusField.moods.sections.music.Music;

import java.util.List;
import java.util.Optional;

public class MusicMapper {

    public static Music fromRequest(MusicRequest req) {
        if (req == null) return null;

        return Music.builder()
                .id(req.getId())
                .title(req.getTitle())
                .playlistUrl(req.getPlaylistUrl())
                .tags(Optional.ofNullable(req.getTags()).orElse(List.of()))
                .scope(req.getScope())
                .audius(req.getAudius())
                .build();
    }
}

