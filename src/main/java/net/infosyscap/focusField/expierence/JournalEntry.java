package net.infosyscap.focusField.expierence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "journal_entries")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_entries_seq")
    @SequenceGenerator(name = "journal_entries_seq", sequenceName = "journal_entries_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long logId;

    @Column(nullable = false)
    private String type;

    private String content;

    private String moodSlug;

    private String language;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
