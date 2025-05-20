package net.infosyscap.focusField.moods.single;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.infosyscap.focusField.moods.list.MoodList;
import net.infosyscap.focusField.moods.list.MoodListRepository;
import net.infosyscap.focusField.moods.modal.*;
import net.infosyscap.focusField.moods.sections.breathing.Breathing;
import net.infosyscap.focusField.moods.sections.breathing.BreathingPhases;
import net.infosyscap.focusField.moods.sections.breathing.BreathingPhasesRequest;
import net.infosyscap.focusField.moods.sections.coach.Coach;
import net.infosyscap.focusField.moods.sections.coach.CoachAnswer;
import net.infosyscap.focusField.moods.sections.coach.CoachStep;
import net.infosyscap.focusField.moods.sections.coach.CoachStepRepository;
import net.infosyscap.focusField.moods.sections.cta.Cta;
import net.infosyscap.focusField.moods.sections.environment.Environment;
import net.infosyscap.focusField.moods.sections.journal.Journal;
import net.infosyscap.focusField.moods.sections.journal.JournalGoal;
import net.infosyscap.focusField.moods.sections.music.Music;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxBody;
import net.infosyscap.focusField.moods.sections.relaxbody.RelaxExercise;
import net.infosyscap.focusField.moods.sections.spiritual.Spiritual;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoodService {
    private final MoodListRepository moodListRepository;
    private final MoodRepository moodRepository;
    private final CoachStepRepository coachStepRepository;

    public Mood saveMood(MoodRequest moodRequest) {
        MoodList moodList = moodListRepository.findById(moodRequest.getMoodListId())
                .orElseThrow(() -> new IllegalArgumentException("MoodList not found with id: " + moodRequest.getMoodListId()));

        Music music = Music.builder()
                .title(moodRequest.getMusic().getTitle())
                .playlistUrl(moodRequest.getMusic().getPlaylistUrl())
                .tags(moodRequest.getMusic().getTags())
                .scope(moodRequest.getMusic().getScope())
                .audius(moodRequest.getMusic().getAudius())
                .build();

        List<BreathingPhases> breathingPhasesList = moodRequest.getBreathing().getPhases().stream()
                .map(phaseReq -> BreathingPhases.builder()
                        .phase(phaseReq.getPhase())
                        .phaseLabel(phaseReq.getPhaseLabel())
                        .duration(phaseReq.getDuration() != null ? phaseReq.getDuration() : 0)
                        .mode(phaseReq.getMode())
                        .build())
                .collect(Collectors.toList());


        Breathing  breathing = Breathing.builder()
                .enabled(moodRequest.getBreathing().getEnabled())
                .techniqueLabel(moodRequest.getBreathing().getTechniqueLabel())
                .technique(moodRequest.getBreathing().getTechnique())
                .rounds(moodRequest.getBreathing().getRounds())
                .totalDurationLabel(moodRequest.getBreathing().getTotalDurationLabel())
                .totalDuration(moodRequest.getBreathing().getTotalDuration())
                .scope(moodRequest.getBreathing().getScope())
                .start(moodRequest.getBreathing().getStart())
                .stop(moodRequest.getBreathing().getStop())
                .build();

        breathingPhasesList.forEach(p -> p.setBreathing(breathing));
        breathing.setPhases(breathingPhasesList);

        List<RelaxExercise> relaxExercises = moodRequest.getRelaxBody().getExercises().stream()
                .map(exerciseReq -> RelaxExercise.builder()
                        .name(exerciseReq.getName())
                        .instructions(exerciseReq.getInstructions())
                        .duration(exerciseReq.getDuration())
                        .image(exerciseReq.getImage())
                        .build())
                .collect(Collectors.toList());

        RelaxBody relaxBody = RelaxBody.builder()
                .enabled(moodRequest.getRelaxBody().getEnabled())
                .title(moodRequest.getRelaxBody().getTitle())
                .description(moodRequest.getRelaxBody().getDescription())
                .start(moodRequest.getRelaxBody().getStart())
                .stop(moodRequest.getRelaxBody().getStop())
                .pause(moodRequest.getRelaxBody().getPause())
                .pauseText(moodRequest.getRelaxBody().getPauseText())
                .duration(moodRequest.getRelaxBody().getDuration())
                .scrollDown(moodRequest.getRelaxBody().getScrollDown())
                .scrollUp(moodRequest.getRelaxBody().getScrollUp())
                .completed(moodRequest.getRelaxBody().getCompleted())
                .repeatIn(moodRequest.getRelaxBody().getRepeatIn())
                .pauseDuration(moodRequest.getRelaxBody().getPauseDuration())
                .build();

        relaxExercises.forEach(e -> e.setRelaxBody(relaxBody));
        relaxBody.setExercises(relaxExercises);

        Journal journalPre = Journal.builder()
                .enabled(moodRequest.getJournalPre().getEnabled())
                .prompt(moodRequest.getJournalPre().getPrompt())
                .placeholder(moodRequest.getJournalPre().getPlaceholder())
                .save(moodRequest.getJournalPre().getSave())
                .optional(moodRequest.getJournalPre().getOptional())
                .build();

        Journal journalPost = Journal.builder()
                .enabled(moodRequest.getJournalPost().getEnabled())
                .prompt(moodRequest.getJournalPost().getPrompt())
                .placeholder(moodRequest.getJournalPost().getPlaceholder())
                .save(moodRequest.getJournalPost().getSave())
                .optional(moodRequest.getJournalPost().getOptional())
                .build();

        Spiritual spiritual = Spiritual.builder()
                .enabled(moodRequest.getSpiritual().getEnabled())
                .type(moodRequest.getSpiritual().getType())
                .verse(moodRequest.getSpiritual().getVerse())
                .text(moodRequest.getSpiritual().getText())
                .build();

        Environment environment = Environment.builder()
                .enabled(moodRequest.getEnvironment().getEnabled())
                .backgroundImage(moodRequest.getEnvironment().getBackgroundImage())
                .backgroundVideo(moodRequest.getEnvironment().getBackgroundVideo())
                .soundscape(moodRequest.getEnvironment().getSoundscape())
                .audioSrc(moodRequest.getEnvironment().getAudioSrc())
                .suggestion(moodRequest.getEnvironment().getSuggestion())
                .duration(moodRequest.getEnvironment().getDuration())
                .title(moodRequest.getEnvironment().getTitle())
                .start(moodRequest.getEnvironment().getStart())
                .stop(moodRequest.getEnvironment().getStop())
                .mute(moodRequest.getEnvironment().getMute())
                .unmute(moodRequest.getEnvironment().getUnmute())
                .fullscreen(moodRequest.getEnvironment().getFullscreen())
                .exitFullscreen(moodRequest.getEnvironment().getExitFullscreen())
                .suggestedDuration(moodRequest.getEnvironment().getSuggestedDuration())
                .build();
        if (moodRequest.getEnvironment().getEnabled()) {
            environment.setSoundscape(moodRequest.getEnvironment().getSoundscape());
        }

        Coach coach = Coach.builder()
                .enabled(moodRequest.getCoach().getEnabled())
                .intro(moodRequest.getCoach().getIntro())
                .obstacle(moodRequest.getCoach().getObstacle())
                .situation(moodRequest.getCoach().getSituation())
                .feedback(moodRequest.getCoach().getFeedback())
                .next(moodRequest.getCoach().getNext())
                .finished(moodRequest.getCoach().getFinished())
                .noSteps(moodRequest.getCoach().getNoSteps())
                .build();

        if (moodRequest.getCoach().getSteps() != null) {
            List<CoachStep> coachSteps = moodRequest.getCoach().getSteps().stream()
                    .map(stepReq -> {
                        CoachStep step = CoachStep.builder()
                                .situation(stepReq.getSituation())
                                .coach(coach)
                                .build();

                        if (stepReq.getAnswers() != null) {
                            List<CoachAnswer> answers = stepReq.getAnswers().stream()
                                    .map(answerReq -> CoachAnswer.builder()
                                            .text(answerReq.getText())
                                            .correct(answerReq.getCorrect())
                                            .feedback(answerReq.getFeedback())
                                            .step(step)
                                            .build())
                                    .collect(Collectors.toList());

                            step.setAnswers(answers);
                        }

                        return step;
                    })
                    .collect(Collectors.toList());

            coach.setSteps(coachSteps);
        }

        Cta cta = Cta.builder()
                .text(moodRequest.getCta().getText())
                .actionCta(moodRequest.getCta().getActionCta())
                .build();


        InfoModal infoModal = InfoModal.builder()
                .title(moodRequest.getMoodModal().getInfoModal().getTitle())
                .build();

       CtaModal ctaModal = CtaModal.builder()
                .defaultText(moodRequest.getMoodModal().getCtaModal().getDefaultText())
                .build();

        ModalTitle modalTitle = ModalTitle.builder()
                .calm(moodRequest.getMoodModal().getTitle().getCalm())
                .build();

        ModalDesc modalDesc = ModalDesc.builder()
                .calm(moodRequest.getMoodModal().getDesc().getCalm())
                .build();


        MoodSections moodSections = MoodSections.builder()
                .music(moodRequest.getMoodModal().getSections().getMusic())
                .goals(moodRequest.getMoodModal().getSections().getGoals())
                .preJournal(moodRequest.getMoodModal().getSections().getPreJournal())
                .breathing(moodRequest.getMoodModal().getSections().getBreathing())
                .relaxBody(moodRequest.getMoodModal().getSections().getRelaxBody())
                .coach(moodRequest.getMoodModal().getSections().getCoach())
                .ambient(moodRequest.getMoodModal().getSections().getAmbient())
                .spiritual(moodRequest.getMoodModal().getSections().getSpiritual())
                .postJournal(moodRequest.getMoodModal().getSections().getPostJournal())
                .build();


        MoodModal moodModal = MoodModal.builder()
                .loading(moodRequest.getMoodModal().getLoading())
                .notFound(moodRequest.getMoodModal().getNotFound())
                .infoModal(infoModal)
                .ctaModal(ctaModal)
                .title(modalTitle)
                .desc(modalDesc)
                .sections(moodSections)
                .build();


        Mood mood = Mood.builder()
                .name(moodRequest.getName())
                .slug(moodRequest.getSlug())
                .lang(moodRequest.getLang())
                .description(moodRequest.getDescription())
                .imagine(moodRequest.getImagine())
                .helpYou(moodRequest.getHelpYou())
                .durationSuggestion(moodRequest.getDurationSuggestion())
                .music(music)
                .breathing(breathing)
                .relaxBody(relaxBody)
                .journalPre(journalPre)
                .journalPost(journalPost)
                .spiritual(spiritual)
                .environment(environment)
                .coach(coach)
                .cta(cta)
                .moodModal(moodModal)
                .moodList(moodList)
                .build();

        return moodRepository.save(mood);
    }


    public Mood getMoodBySlugAndLang(String slug, String lang) {
        return moodRepository.findBySlugAndLang(slug, lang)
                .orElseThrow(() -> new EntityNotFoundException("Mood not found"));
    }

    public Mood updateMood(String slug, String lang, MoodRequest moodRequest) {
        Mood existingMood = getMoodBySlugAndLang(slug, lang);

        MoodList moodList = moodListRepository.findById(moodRequest.getMoodListId())
                .orElseThrow(() -> new IllegalArgumentException("MoodList not found with id: " + moodRequest.getMoodListId()));

        Music music = Music.builder()
                .id(existingMood.getMusic() != null ? existingMood.getMusic().getId() : null)
                .title(moodRequest.getMusic().getTitle())
                .playlistUrl(moodRequest.getMusic().getPlaylistUrl())
                .tags(moodRequest.getMusic().getTags())
                .scope(moodRequest.getMusic().getScope())
                .audius(moodRequest.getMusic().getAudius())
                .build();

        List<BreathingPhases> breathingPhasesList = moodRequest.getBreathing().getPhases().stream()
                .map(phaseReq -> BreathingPhases.builder()
                        .phase(phaseReq.getPhase())
                        .phaseLabel(phaseReq.getPhaseLabel())
                        .duration(phaseReq.getDuration() != null ? phaseReq.getDuration() : 0)
                        .mode(phaseReq.getMode())
                        .build())
                .collect(Collectors.toList());

        Breathing breathing = Breathing.builder()
                .id(existingMood.getBreathing() != null ? existingMood.getBreathing().getId() : null)
                .enabled(moodRequest.getBreathing().getEnabled())
                .techniqueLabel(moodRequest.getBreathing().getTechniqueLabel())
                .technique(moodRequest.getBreathing().getTechnique())
                .rounds(moodRequest.getBreathing().getRounds())
                .totalDurationLabel(moodRequest.getBreathing().getTotalDurationLabel())
                .totalDuration(moodRequest.getBreathing().getTotalDuration())
                .scope(moodRequest.getBreathing().getScope())
                .start(moodRequest.getBreathing().getStart())
                .stop(moodRequest.getBreathing().getStop())
                .build();

        breathingPhasesList.forEach(p -> p.setBreathing(breathing));
        breathing.setPhases(breathingPhasesList);

        List<RelaxExercise> relaxExercises = moodRequest.getRelaxBody().getExercises().stream()
                .map(exerciseReq -> RelaxExercise.builder()
                        .name(exerciseReq.getName())
                        .instructions(exerciseReq.getInstructions())
                        .duration(exerciseReq.getDuration())
                        .image(exerciseReq.getImage())
                        .build())
                .collect(Collectors.toList());

        RelaxBody relaxBody = RelaxBody.builder()
                .id(existingMood.getRelaxBody() != null ? existingMood.getRelaxBody().getId() : null)
                .enabled(moodRequest.getRelaxBody().getEnabled())
                .title(moodRequest.getRelaxBody().getTitle())
                .description(moodRequest.getRelaxBody().getDescription())
                .start(moodRequest.getRelaxBody().getStart())
                .stop(moodRequest.getRelaxBody().getStop())
                .pause(moodRequest.getRelaxBody().getPause())
                .pauseText(moodRequest.getRelaxBody().getPauseText())
                .duration(moodRequest.getRelaxBody().getDuration())
                .scrollDown(moodRequest.getRelaxBody().getScrollDown())
                .scrollUp(moodRequest.getRelaxBody().getScrollUp())
                .completed(moodRequest.getRelaxBody().getCompleted())
                .repeatIn(moodRequest.getRelaxBody().getRepeatIn())
                .pauseDuration(moodRequest.getRelaxBody().getPauseDuration())
                .build();

        relaxExercises.forEach(e -> e.setRelaxBody(relaxBody));
        relaxBody.setExercises(relaxExercises);

        Journal journalPre = Journal.builder()
                .id(existingMood.getJournalPre() != null ? existingMood.getJournalPre().getId() : null)
                .enabled(moodRequest.getJournalPre().getEnabled())
                .prompt(moodRequest.getJournalPre().getPrompt())
                .placeholder(moodRequest.getJournalPre().getPlaceholder())
                .save(moodRequest.getJournalPre().getSave())
                .optional(moodRequest.getJournalPre().getOptional())
                .build();

        Journal journalPost = Journal.builder()
                .id(existingMood.getJournalPost() != null ? existingMood.getJournalPost().getId() : null)
                .enabled(moodRequest.getJournalPost().getEnabled())
                .prompt(moodRequest.getJournalPost().getPrompt())
                .placeholder(moodRequest.getJournalPost().getPlaceholder())
                .save(moodRequest.getJournalPost().getSave())
                .optional(moodRequest.getJournalPost().getOptional())
                .build();

        List<JournalGoal> goalItems = moodRequest.getJournalGoals().getGoals().stream()
                .map(req -> JournalGoal.builder()
                        .goal(req.getGoal())
                        .how(req.getHow())
                        .build())
                .toList();

        Journal journalGoals = Journal.builder()
                .id(existingMood.getJournalGoals() != null ? existingMood.getJournalGoals().getId() : null)
                .enabled(moodRequest.getJournalGoals().getEnabled())
                .prompt(moodRequest.getJournalGoals().getPrompt())
                .placeholder(moodRequest.getJournalGoals().getPlaceholder())
                .save(moodRequest.getJournalGoals().getSave())
                .optional(moodRequest.getJournalGoals().getOptional())
                .goalLabel(moodRequest.getJournalGoals().getGoalLabel())
                .goals(goalItems)
                .build();

        goalItems.forEach(item -> item.setJournal(journalGoals));

        Spiritual spiritual = Spiritual.builder()
                .id(existingMood.getSpiritual() != null ? existingMood.getSpiritual().getId() : null)
                .enabled(moodRequest.getSpiritual().getEnabled())
                .type(moodRequest.getSpiritual().getType())
                .verse(moodRequest.getSpiritual().getVerse())
                .text(moodRequest.getSpiritual().getText())
                .build();

        Environment environment = Environment.builder()
                .id(existingMood.getEnvironment() != null ? existingMood.getEnvironment().getId() : null)
                .enabled(moodRequest.getEnvironment().getEnabled())
                .backgroundImage(moodRequest.getEnvironment().getBackgroundImage())
                .backgroundVideo(moodRequest.getEnvironment().getBackgroundVideo())
                .soundscape(moodRequest.getEnvironment().getSoundscape())
                .audioSrc(moodRequest.getEnvironment().getAudioSrc())
                .suggestion(moodRequest.getEnvironment().getSuggestion())
                .duration(moodRequest.getEnvironment().getDuration())
                .title(moodRequest.getEnvironment().getTitle())
                .start(moodRequest.getEnvironment().getStart())
                .stop(moodRequest.getEnvironment().getStop())
                .mute(moodRequest.getEnvironment().getMute())
                .unmute(moodRequest.getEnvironment().getUnmute())
                .fullscreen(moodRequest.getEnvironment().getFullscreen())
                .exitFullscreen(moodRequest.getEnvironment().getExitFullscreen())
                .suggestedDuration(moodRequest.getEnvironment().getSuggestedDuration())
                .build();

        // Gestione Coach
        Coach coach = existingMood.getCoach() != null ? existingMood.getCoach() : new Coach();
        coach.setEnabled(moodRequest.getCoach().getEnabled());
        coach.setIntro(moodRequest.getCoach().getIntro());
        coach.setObstacle(moodRequest.getCoach().getObstacle());
        coach.setSituation(moodRequest.getCoach().getSituation());
        coach.setFeedback(moodRequest.getCoach().getFeedback());
        coach.setNext(moodRequest.getCoach().getNext());
        coach.setFinished(moodRequest.getCoach().getFinished());
        coach.setNoSteps(moodRequest.getCoach().getNoSteps());

        List<CoachStep> coachSteps = moodRequest.getCoach().getSteps().stream()
                .map(stepReq -> {
                    // Cerca il CoachStep usando la situazione (o altra proprietà unica)
                    CoachStep step = coachStepRepository.findBySituation(stepReq.getSituation())
                            .orElse(new CoachStep());
                    step.setSituation(stepReq.getSituation());
                    step.setCoach(coach);
                    return step;
                })
                .collect(Collectors.toList());

        // Aggiorna i passi del Coach
        coach.setSteps(coachSteps);
        Cta cta = Cta.builder()
                .id(existingMood.getCta() != null ? existingMood.getCta().getId() : null)
                .text(moodRequest.getCta().getText())
                .actionCta(moodRequest.getCta().getActionCta())
                .build();

        InfoModal infoModal = InfoModal.builder()
                .id(existingMood.getMoodModal().getInfoModal().getId())
                .title(moodRequest.getMoodModal().getInfoModal().getTitle())
                .build();

        CtaModal ctaModal = CtaModal.builder()
                .id(existingMood.getMoodModal().getCtaModal().getId())
                .defaultText(moodRequest.getMoodModal().getCtaModal().getDefaultText())
                .build();

        ModalTitle modalTitle = ModalTitle.builder()
                .id(existingMood.getMoodModal().getTitle().getId())
                .calm(moodRequest.getMoodModal().getTitle().getCalm())
                .build();

        ModalDesc modalDesc = ModalDesc.builder()
                .id(existingMood.getMoodModal().getDesc().getId())
                .calm(moodRequest.getMoodModal().getDesc().getCalm())
                .build();

        MoodSections moodSections = MoodSections.builder()
                .id(existingMood.getMoodModal().getSections().getId())
                .music(moodRequest.getMoodModal().getSections().getMusic())
                .goals(moodRequest.getMoodModal().getSections().getGoals())
                .preJournal(moodRequest.getMoodModal().getSections().getPreJournal())
                .breathing(moodRequest.getMoodModal().getSections().getBreathing())
                .relaxBody(moodRequest.getMoodModal().getSections().getRelaxBody())
                .coach(moodRequest.getMoodModal().getSections().getCoach())
                .ambient(moodRequest.getMoodModal().getSections().getAmbient())
                .spiritual(moodRequest.getMoodModal().getSections().getSpiritual())
                .postJournal(moodRequest.getMoodModal().getSections().getPostJournal())
                .build();

        MoodModal moodModal = MoodModal.builder()
                .id(existingMood.getMoodModal().getId())
                .loading(moodRequest.getMoodModal().getLoading())
                .notFound(moodRequest.getMoodModal().getNotFound())
                .infoModal(infoModal)
                .ctaModal(ctaModal)
                .title(modalTitle)
                .desc(modalDesc)
                .sections(moodSections)
                .build();

        existingMood.setName(moodRequest.getName());
        existingMood.setDescription(moodRequest.getDescription());
        existingMood.setImagine(moodRequest.getImagine());
        existingMood.setHelpYou(moodRequest.getHelpYou());
        existingMood.setDurationSuggestion(moodRequest.getDurationSuggestion());
        existingMood.setMusic(music);
        existingMood.setBreathing(breathing);
        existingMood.setRelaxBody(relaxBody);
        existingMood.setJournalPre(journalPre);
        existingMood.setJournalPost(journalPost);
        existingMood.setSpiritual(spiritual);
        existingMood.setEnvironment(environment);
        existingMood.setCoach(coach);
        existingMood.setCta(cta);
        existingMood.setMoodModal(moodModal);
        existingMood.setMoodList(moodList);

        return moodRepository.save(existingMood);
    }

}