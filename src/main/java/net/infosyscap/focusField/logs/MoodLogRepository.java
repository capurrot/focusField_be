package net.infosyscap.focusField.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoodLogRepository extends JpaRepository<MoodLog, Long> {
    List<MoodLog> findByUserId(Long userId);
    @Query("SELECT new net.infosyscap.focusField.logs.MoodLogCount(l.moodSlug, COUNT(l)) " +
            "FROM MoodLog l GROUP BY l.moodSlug ORDER BY COUNT(l) DESC")
    List<MoodLogResponse> findTopMoodLogs();
}
