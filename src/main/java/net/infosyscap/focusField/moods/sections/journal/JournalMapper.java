package net.infosyscap.focusField.moods.sections.journal;

import net.infosyscap.focusField.moods.sections.journal.Journal;

public class JournalMapper {

    public static Journal fromRequest(JournalRequest req) {
        if (req == null) return null;

        return Journal.builder()
                .id(req.getId())
                .enabled(req.isEnabled())
                .prompt(req.getPrompt())
                .placeholder(req.getPlaceholder())
                .save(req.getSave())
                .optional(req.isOptional())
                .build();
    }
}

