package net.infosyscap.focusField.moods.sections.journal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalGoalResponse {
    private String goal;
    private String how;
}
