package net.infosyscap.focusField.moods.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModalTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modal_title_seq")
    @SequenceGenerator(name = "modal_title_seq", sequenceName = "modal_title_id_seq", allocationSize = 1)
    private Long id;

    private String calm;
}

