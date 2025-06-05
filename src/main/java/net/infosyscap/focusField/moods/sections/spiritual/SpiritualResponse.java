package net.infosyscap.focusField.moods.sections.spiritual;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpiritualResponse {
    private Long id;
    private Boolean enabled;
    private String type;
    private String verse;
    private String text;
}
