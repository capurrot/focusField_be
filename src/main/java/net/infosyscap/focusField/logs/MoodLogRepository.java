package net.infosyscap.focusField.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoodLogRepository extends JpaRepository<MoodLog, Long> {
    List<MoodLog> findByUserId(Long userId);
}
