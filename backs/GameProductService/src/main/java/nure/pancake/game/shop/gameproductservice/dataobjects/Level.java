package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    private Long id;
    private String name;
    private String description;
    private float price;
    private String mainImage;
    private List<String> images;
    private Long gameId;
    private boolean hidden;
}
