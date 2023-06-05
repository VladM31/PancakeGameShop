package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.Platforms;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameRespond {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer ageRating;
    private LocalDateTime releaseDate;
    private String icon;
    private String mainImage;
    private String videoUrl;
    private List<String> platforms;
    private List<String> images;
    private Set<String> genres;
}
