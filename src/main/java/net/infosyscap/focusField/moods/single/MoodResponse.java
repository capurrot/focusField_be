package net.infosyscap.focusField.moods.single;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.infosyscap.focusField.moods.list.MoodListResponse;
import net.infosyscap.focusField.moods.modal.MoodModalResponse;
import net.infosyscap.focusField.moods.sections.breathing.BreathingResponse;
import net.infosyscap.focusField.moods.sections.coach.CoachResponse;
import net.infosyscap.focusField.moods.sections.cta.CtaResponse;
import net.infosyscap.focusField.moods.sections.environment.EnvironmentResponse;
import net.infosyscap.focusField.moods.sections.journal.JournalResponse;
import net.infosyscap.focusField.moods.sections.music.MusicResponse;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxBodyResponse;
import net.infosyscap.focusField.moods.sections.spiritual.SpiritualResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoodResponse {
    private Long id;
    private String slug;
    private String lang;
    private String name;
    private String description;
    private String imagine;
    private String helpYou;
    private MusicResponse music;
    private BreathingResponse breathing;
    private RelaxBodyResponse relaxBody;
    private JournalResponse journalPre;
    private JournalResponse journalPost;
    private SpiritualResponse spiritual;
    private EnvironmentResponse environment;
    private CoachResponse coach;
    private CtaResponse cta;
    private MoodModalResponse moodModal;
    private String durationSuggestion;
    private Long moodListId;

}

