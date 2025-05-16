package net.infosyscap.focusField.moods.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalDescResponse {
    private Long id;
    private String calm;
}
