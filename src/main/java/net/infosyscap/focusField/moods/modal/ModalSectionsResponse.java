package net.infosyscap.focusField.moods.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModalSectionsResponse {
    private Long id;
    private String music;
    private String goals;
    private String preJournal;
    private String breathing;
    private String relaxBody;
    private String coach;
    private String ambient;
    private String spiritual;
    private String postJournal;
}
