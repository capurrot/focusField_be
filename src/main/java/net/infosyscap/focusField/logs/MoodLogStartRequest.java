package net.infosyscap.focusField.logs;

import lombok.Data;

@Data
public class MoodLogStartRequest {
    private Long userId;
    private String moodSlug;
    private String language;

}
