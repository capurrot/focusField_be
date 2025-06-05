package net.infosyscap.focusField.moods.sections.cta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CtaResponse {
    private Long id;
    private String actionCta;
    private String text;
}
