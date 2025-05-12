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
public class ModalDesc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modal_desc_seq")
    @SequenceGenerator(name = "modal_desc_seq", sequenceName = "modal_desc_id_seq", allocationSize = 1)
    private Long id;

    private String calm;
}

