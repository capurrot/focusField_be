package net.infosyscap.focusField.moods.list;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodListRepository extends JpaRepository<MoodList, Long> {
    MoodList save(MoodList mood);
    List<MoodList> findAllByOrderByIdAsc();

}