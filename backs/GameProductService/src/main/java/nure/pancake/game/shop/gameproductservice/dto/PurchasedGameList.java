package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class PurchasedGameList extends AbstractDtoList<PurchasedGameList.PurchasedGameDto> {

    public PurchasedGameList(Page<PurchasedGameDto> pages) {
        super(pages);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchasedGameDto{
        private Long id;
        private LocalDateTime buyDate;
        private Long userId;
        private Long gamesId;
        private List<PGLevelDto> levels;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PGLevelDto{
        private Long id;
        private LocalDateTime buyDate;
        private Long levelsId;
    }
}
