package nure.pancake.game.shop.gameproductservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

public class PurchasedGameIdsList extends AbstractDtoList<PurchasedGameIdsList.PurGameIds> {

    public PurchasedGameIdsList(Page<PurGameIds> pages) {
        super(pages);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurGameIds {
        private Long gameId;
        private List<Long> levelIds;
    }
}
