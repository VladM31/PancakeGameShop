package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameLevelsRespond {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer ageRating;
    private LocalDateTime releaseDate;
    private String icon;
    private String mainImage;
    private String videoUrl;
    private List<String> images;
    private List<String> platforms;
    private Set<String> genres;
    private List<LevelRespond> levels;
}
