package net.infosyscap.focusField.moods.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoodListResponse {
    private Long id;
    private String slug;
    private String image;
    private String background;
    private String opacity;
    private String icon;
    private List<String> tag;
    private List<String> colors;
}
