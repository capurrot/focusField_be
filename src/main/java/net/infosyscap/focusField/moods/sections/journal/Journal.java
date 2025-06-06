package net.infosyscap.focusField.moods.sections.journal;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_seq")
    @SequenceGenerator(name = "journal_seq", sequenceName = "journal_id_seq", allocationSize = 1)
    private Long id;

    private Boolean enabled;
    @Column(length = 1000)
    private String prompt;
    private String placeholder;
    private String save;
    private Boolean optional;


    private String goalLabel; // Es. "Obiettivo n. {{index}}"

    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalGoal> goals;
}