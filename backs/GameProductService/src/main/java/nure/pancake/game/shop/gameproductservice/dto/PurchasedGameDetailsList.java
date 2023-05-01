package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class PurchasedGameDetailsList extends AbstractDtoList<PurchasedGameDetailsList.PurchasedGameDetailsDto> {
    public PurchasedGameDetailsList(Page<PurchasedGameDetailsDto> pages) {
        super(pages);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchasedGameDetailsDto {
        private Long id;
        private Long userId;
        private Long gamesId;
        private String name;
        private String mainImage;
        private LocalDateTime buyDate;
        private List<nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails.PGLevelDetails> levels;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PGLevelDetailsDto{
        private Long id;
        private Long levelsId;
        private String name;
        private String mainImage;
        private LocalDateTime buyDate;
    }
}
