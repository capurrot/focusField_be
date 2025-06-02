package net.infosyscap.focusField.expierence;

import lombok.Data;

@Data
public class JournalEntryRequest {
    private Long userId;
    private Long logId;
    private String type;
    private String content;
    private String moodSlug;
    private String language;
}