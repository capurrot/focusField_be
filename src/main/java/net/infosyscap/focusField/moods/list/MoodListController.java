package net.infosyscap.focusField.moods.list;

import net.infosyscap.focusField.commons.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/focus-field/moods")
public class MoodListController {
    @Autowired
    MoodListService moodListService;
    // GET per trovare tutti i moods
    @GetMapping
    public ResponseEntity<List<MoodListDto>> getAllMoodsSimple() {
        return ResponseEntity.ok(moodListService.findAllMoodDtos());
    }
    // GET per trovare un mood specifico in base al suo slug
    @GetMapping("/slug/{slug}")
    public MoodList findMoodBySlug(@PathVariable("slug") String slug) {
        return moodListService.findMoodBySlug(slug);
    }
    // GET per trovare un mood specifico in base al suo id
    @GetMapping("/{id}")
    public MoodListDto findMoodById(@PathVariable("id") Long id) {
        return moodListService.findMoodDtoById(id);
    }
    // POST per creare un nuovo mood
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse saveMood(@RequestBody MoodList mood) {
        return moodListService.saveMood(mood);
    }
    // PUT per aggiornare un mood specifico in base al suo id
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public MoodList updateMood(@PathVariable Long id, @RequestBody MoodList mood) {
        return moodListService.updateMood(id, mood);
    }
    // DELETE per eliminare un mood specifico in base al suo id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMood(@PathVariable Long id) {
        moodListService.deleteMood(id);
    }



}
