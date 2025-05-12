package net.infosyscap.focusField.moods.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.DoubleStream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoodSections {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_sections_seq")
    @SequenceGenerator(name = "mood_sections_seq", sequenceName = "mood_sections_id_seq", allocationSize = 1)
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

