package net.infosyscap.focusField.moods.list;

import java.util.List;

public record MoodListDto(
        Long id, String slug,
        String image,
        String background,
        List<String> tag,
        List<String> colors,
        String opacity,
        String icon
) {}
