package net.infosyscap.focusField.moods.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoodColorsResponse {
    private Long id;
    private String color;
}
