package net.infosyscap.focusField.logs;

import lombok.RequiredArgsConstructor;
import net.infosyscap.focusField.users.AppUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/focus-field/log")
@RequiredArgsConstructor
public class MoodLogController {

    private final MoodLogRepository moodLogRepository;

    @PostMapping("/start")
    public MoodLog startMoodLog(@RequestBody MoodLogStartRequest request) {
        MoodLog log = MoodLog.builder()
                .userId(request.getUserId())
                .moodSlug(request.getMoodSlug())
                .language(request.getLanguage())
                .startTime(LocalDateTime.now())
                .completed(false)
                .build();
        return moodLogRepository.save(log);
    }

    @PutMapping("/end/{id}")
    public MoodLog endMoodLog(@PathVariable Long id) {
        MoodLog log = moodLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log non trovato"));
        LocalDateTime now = LocalDateTime.now();
        log.setEndTime(now);
        log.setCompleted(true);
        log.setDurationSeconds((int) Duration.between(log.getStartTime(), now).getSeconds());
        return moodLogRepository.save(log);
    }

    @GetMapping("/user/logs")
    @PreAuthorize("isAuthenticated()")
    public List<MoodLog> getAuthenticatedUserLogs(Authentication authentication) {
        AppUser user = (AppUser) authentication.getPrincipal();

        // Se ha il ruolo ADMIN, restituisci tutti i log
        if (authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return moodLogRepository.findAll();
        }

        // Altrimenti restituisci solo i log dell'utente
        return moodLogRepository.findByUserId(user.getId());
    }



}
