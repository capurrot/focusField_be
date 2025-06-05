package net.infosyscap.focusField.moods.single;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.infosyscap.focusField.moods.list.MoodList;
import net.infosyscap.focusField.moods.sections.breathing.Breathing;
import net.infosyscap.focusField.moods.modal.MoodModal;
import net.infosyscap.focusField.moods.sections.coach.Coach;
import net.infosyscap.focusField.moods.sections.cta.Cta;
import net.infosyscap.focusField.moods.sections.environment.Environment;
import net.infosyscap.focusField.moods.sections.journal.Journal;
import net.infosyscap.focusField.moods.sections.music.Music;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxBody;
import net.infosyscap.focusField.moods.sections.spiritual.Spiritual;

@Entity
@Table(name = "moods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mood {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mood_seq")
    @SequenceGenerator(name = "mood_seq", sequenceName = "mood_id_seq", allocationSize = 1)
    private Long id;

    private String slug;
    private String lang;

    private String name;
    private String description;
    private String imagine;
    private String helpYou;

    @OneToOne(cascade = CascadeType.ALL)
    private Music music;

    @OneToOne(cascade = CascadeType.ALL)
    private MoodModal moodModal;

    @OneToOne(cascade = CascadeType.ALL)
    private Breathing breathing;

    @OneToOne(cascade = CascadeType.ALL)
    private RelaxBody relaxBody;

    @OneToOne(cascade = CascadeType.ALL)
    private Journal journalPre;

    @OneToOne(cascade = CascadeType.ALL)
    private Journal journalPost;

    @OneToOne(cascade = CascadeType.ALL)
    private Spiritual spiritual;

    @OneToOne(cascade = CascadeType.ALL)
    private Environment environment;

    @OneToOne(cascade = CascadeType.ALL)
    private Coach coach;

    private String durationSuggestion;

    @OneToOne(cascade = CascadeType.ALL)
    private Cta cta;

    @ManyToOne
    @JoinColumn(name = "mood_list_id")
    private MoodList moodList;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "journal_goals_id")
    private Journal journalGoals;
}