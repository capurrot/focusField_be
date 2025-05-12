package net.infosyscap.focusField.moods.single;

import net.infosyscap.focusField.moods.list.MoodList;
import net.infosyscap.focusField.moods.list.MoodListRepository;
import net.infosyscap.focusField.moods.modal.MoodModalMapper;
import net.infosyscap.focusField.moods.sections.breathing.BreathingMapper;
import net.infosyscap.focusField.moods.sections.coach.CoachMapper;
import net.infosyscap.focusField.moods.sections.cta.CtaMapper;
import net.infosyscap.focusField.moods.sections.environment.EnvironmentMapper;
import net.infosyscap.focusField.moods.sections.journal.JournalMapper;
import net.infosyscap.focusField.moods.sections.music.MusicMapper;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxBodyMapper;
import net.infosyscap.focusField.moods.sections.spiritual.SpiritualMapper;

public class MoodMapper {

    public static Mood fromRequest(MoodRequest req, MoodListRepository moodListRepo) {
        if (req == null) return null;

        Mood mood = new Mood();
        mood.setId(req.getId());
        mood.setSlug(req.getSlug());
        mood.setLang(req.getLang());
        mood.setName(req.getName());
        mood.setDescription(req.getDescription());
        mood.setImagine(req.getImagine());
        mood.setHelpYou(req.getHelpYou());
        mood.setDurationSuggestion(req.getDurationSuggestion());

        mood.setMusic(MusicMapper.fromRequest(req.getMusic()));
        mood.setMoodModal(MoodModalMapper.fromRequest(req.getMoodModal()));
        mood.setBreathing(BreathingMapper.fromRequest(req.getBreathing()));
        mood.setRelaxBody(RelaxBodyMapper.fromRequest(req.getRelaxBody()));
        mood.setJournalPre(JournalMapper.fromRequest(req.getJournalPre()));
        mood.setJournalPost(JournalMapper.fromRequest(req.getJournalPost()));
        mood.setSpiritual(SpiritualMapper.fromRequest(req.getSpiritual()));
        mood.setEnvironment(EnvironmentMapper.fromRequest(req.getEnvironment()));
        mood.setCoach(CoachMapper.fromRequest(req.getCoach()));
        mood.setCta(CtaMapper.fromRequest(req.getCta()));

        if (req.getMoodListId() != null) {
            MoodList moodList = moodListRepo.findById(req.getMoodListId()).orElse(null);
            mood.setMoodList(moodList);
        }

        return mood;
    }
}
