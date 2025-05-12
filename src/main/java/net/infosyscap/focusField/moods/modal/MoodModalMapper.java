package net.infosyscap.focusField.moods.modal;

import net.infosyscap.focusField.moods.sections.cta.CtaMapper;

public class MoodModalMapper {

    public static MoodModal fromRequest(MoodModalRequest req) {
        if (req == null) return null;

        return MoodModal.builder()
                .id(req.getId())
                .loading(req.getLoading())
                .notFound(req.getNotFound())
                .infoModal(fromInfoModal(req.getInfoModal()))
                .cta(CtaModalMapper.fromRequest(req.getCta()))
                .title(fromTitle(req.getTitle()))
                .desc(fromDesc(req.getDesc()))
                .sections(fromSections(req.getSections()))
                .build();
    }

    private static InfoModal fromInfoModal(InfoModalRequest req) {
        if (req == null) return null;
        return InfoModal.builder()
                .id(req.getId())
                .title(req.getTitle())
                .build();
    }

    private static ModalTitle fromTitle(ModalTitleRequest req) {
        if (req == null) return null;
        return ModalTitle.builder()
                .id(req.getId())
                .calm(req.getCalm())
                .build();
    }

    private static ModalDesc fromDesc(ModalDescRequest req) {
        if (req == null) return null;
        return ModalDesc.builder()
                .id(req.getId())
                .calm(req.getCalm())
                .build();
    }

    private static MoodSections fromSections(ModalSectionsRequest req) {
        if (req == null) return null;

        return MoodSections.builder()
                .id(req.getId())
                .music(req.getMusic())
                .goals(req.getGoals())
                .preJournal(req.getPreJournal())
                .breathing(req.getBreathing())
                .relaxBody(req.getRelaxBody())
                .coach(req.getCoach())
                .ambient(req.getAmbient())
                .spiritual(req.getSpiritual())
                .postJournal(req.getPostJournal())
                .build();
    }

}

