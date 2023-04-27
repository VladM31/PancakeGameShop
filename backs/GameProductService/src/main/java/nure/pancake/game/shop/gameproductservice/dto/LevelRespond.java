package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRespond {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private String mainImage;
    private List<String> images;
    private Long gameId;
    private Boolean hidden;
}
