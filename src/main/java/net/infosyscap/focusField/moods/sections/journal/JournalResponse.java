package net.infosyscap.focusField.moods.sections.journal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalResponse {
    private Long id;
    private Boolean enabled;
    private String prompt;
    private String placeholder;
    private String save;
    private Boolean optional;
}
