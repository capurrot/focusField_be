package net.infosyscap.focusField.moods.list;

import net.infosyscap.focusField.commons.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MoodListService {
    @Autowired
    MoodListRepository moodListRepository;

    public List<MoodListDto> findAllMoodDtos() {
        return moodListRepository.findAllByOrderByIdAsc()
                .stream()
                .map(mood -> new MoodListDto(
                        mood.getId(),
                        mood.getSlug(),
                        mood.getImage(),
                        mood.getBackground(),
                        mood.getTag()
                                .stream()
                                .map(MoodTags::getTag)
                                .toList(),
                        mood.getColors()
                                .stream()
                                .sorted(Comparator.comparing(MoodColors::getId))
                                .map(MoodColors::getColor)
                                .toList(),
                        mood.getOpacity(),
                        mood.getIcon()
                ))
                .toList();
    }

    public MoodListDto findMoodDtoById(Long id) {
        return moodListRepository.findById(id).map(mood -> new MoodListDto(
                mood.getId(),
                mood.getSlug(),
                mood.getImage(),
                mood.getBackground(),
                mood.getTag()
                        .stream()
                        .map(MoodTags::getTag)
                        .toList(),
                mood.getColors()
                        .stream()
                        .sorted(Comparator.comparing(MoodColors::getId))
                        .map(MoodColors::getColor)
                        .toList(),
                mood.getOpacity(),
                mood.getIcon()
        )).orElse(null);
    }


    public CommonResponse saveMood(MoodList mood) {
        if (mood.getTag() != null) {
            mood.getTag().forEach(tag -> tag.setMood(mood));
        }

        if (mood.getColors() != null) {
            mood.getColors().forEach(color -> color.setMood(mood));
        }

        MoodList saved = moodListRepository.save(mood);
        return new CommonResponse(saved.getId());
    }

    public MoodList updateMood(Long id, MoodList mood) {
        Optional<MoodList> moodList = moodListRepository.findById(id);
        if (moodList.isPresent()) {
            MoodList existingMood = moodList.get();
            existingMood.setSlug(mood.getSlug());
            existingMood.setImage(mood.getImage());
            existingMood.setBackground(mood.getBackground());
            existingMood.setTag(mood.getTag());
            existingMood.setColors(mood.getColors());
            existingMood.setOpacity(mood.getOpacity());
            existingMood.setIcon(mood.getIcon());
            return moodListRepository.save(existingMood);
        } else {
            return null;
        }
    }

    public void deleteMood(Long id) {
        moodListRepository.deleteById(id);
    }
    public MoodList findMoodById(Long id) {
        return moodListRepository.findById(id).orElse(null);
    }

    public MoodList findMoodBySlug(String slug) {
        return moodListRepository.findAll().stream().filter(mood -> mood.getSlug().equals(slug)).findFirst().orElse(null);
    }

}
