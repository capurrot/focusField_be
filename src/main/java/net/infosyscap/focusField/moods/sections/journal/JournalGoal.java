package net.infosyscap.focusField.moods.sections.journal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "JournalGoal")

public class JournalGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_item_seq")
    @SequenceGenerator(name = "goal_item_seq", sequenceName = "goal_item_id_seq", allocationSize = 1)
    private Long id;

    private String goal;
    private String how;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id")
    private Journal journal;

}