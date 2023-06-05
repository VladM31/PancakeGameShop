package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameLevels {
    private Long id;
    private String name;
    private String description;
    private float price;
    private Integer ageRating;
    private LocalDateTime releaseDate;
    private String icon;
    private String mainImage;
    private String videoLink;
    private List<Platforms> platforms;
    private List<String> images;
    private Set<String> genres;
    private List<Level> levels;
}
