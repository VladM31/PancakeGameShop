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
public class PurchasedGame {
    private Long id;
    private LocalDateTime buyDate;
    private Long userId;
    private Long gamesId;
    private List<PGLevel> levels;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PGLevel{
        private Long id;
        private LocalDateTime buyDate;
        private Long levelsId;
    }
}
