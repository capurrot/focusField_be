package net.infosyscap.focusField.expierence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;

    public JournalEntry save(JournalEntryRequest request) {
        JournalEntry entry = new JournalEntry();

        entry.setUserId(request.getUserId());
        entry.setLogId(request.getLogId());
        entry.setType(request.getType());
        entry.setContent(request.getContent());
        entry.setMoodSlug(request.getMoodSlug());
        entry.setLanguage(request.getLanguage());

        return repository.save(entry);
    }
    public List<JournalEntry> findByLogId(Long logId) {
        return repository.findByLogId(logId);
    }
}