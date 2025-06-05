package net.infosyscap.focusField.moods.single;

import lombok.Data;
import net.infosyscap.focusField.moods.modal.MoodModalRequest;
import net.infosyscap.focusField.moods.sections.breathing.BreathingRequest;
import net.infosyscap.focusField.moods.sections.coach.CoachRequest;
import net.infosyscap.focusField.moods.sections.cta.CtaRequest;
import net.infosyscap.focusField.moods.sections.environment.EnvironmentRequest;
import net.infosyscap.focusField.moods.sections.journal.JournalRequest;
import net.infosyscap.focusField.moods.sections.music.MusicRequest;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxBodyRequest;
import net.infosyscap.focusField.moods.sections.spiritual.SpiritualRequest;

@Data
public class MoodRequest {
    private String slug;
    private String lang;
    private String name;
    private String description;
    private String imagine;
    private String helpYou;
    private MusicRequest music;
    private BreathingRequest breathing;
    private RelaxBodyRequest relaxBody;
    private JournalRequest journalPre;
    private JournalRequest journalPost;
    private SpiritualRequest spiritual;
    private EnvironmentRequest environment;
    private CoachRequest coach;
    private CtaRequest cta;
    private MoodModalRequest moodModal;
    private String durationSuggestion;
    private Long moodListId;
    private JournalRequest journalGoals;
}
