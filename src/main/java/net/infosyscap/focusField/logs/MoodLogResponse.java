package net.infosyscap.focusField.logs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoodLogResponse {
    private String moodSlug;
    private long count;
}
