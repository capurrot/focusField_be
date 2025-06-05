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
public class InfoModal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "info_modal_seq")
    @SequenceGenerator(name = "info_modal_seq", sequenceName = "info_modal_id_seq", allocationSize = 1)
    private Long id;

    private String title;
}

