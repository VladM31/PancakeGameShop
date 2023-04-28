package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedGameDetails {
    private Long id;
    private Long userId;
    private Long gamesId;
    private String name;
    private String mainImage;
    private LocalDateTime buyDate;
    private List<PGLevelDetails> levels;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PGLevelDetails{
        private Long id;
        private Long levelsId;
        private String name;
        private String mainImage;
        private LocalDateTime buyDate;
    }
}