package net.infosyscap.focusField.expierence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus-field/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<JournalEntry> save(@RequestBody JournalEntryRequest request) {
        JournalEntry saved = service.save(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/by-log/{logId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<JournalEntry>> getByLog(@PathVariable Long logId) {
        List<JournalEntry> journals = service.findByLogId(logId);
        return ResponseEntity.ok(journals);
    }
}
