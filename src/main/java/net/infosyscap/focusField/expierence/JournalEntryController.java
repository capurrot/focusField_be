package net.infosyscap.focusField.expierence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/focus-field/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;

    @PostMapping
    public ResponseEntity<JournalEntry> save(@RequestBody JournalEntryRequest request) {
        JournalEntry saved = service.save(request);
        return ResponseEntity.ok(saved);
    }
}
