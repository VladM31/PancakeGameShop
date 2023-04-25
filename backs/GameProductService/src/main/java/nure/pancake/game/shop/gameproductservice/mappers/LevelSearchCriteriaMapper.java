package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.LevelFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import org.springframework.stereotype.Component;

@Component
public class LevelSearchCriteriaMapper {

    public LevelSearchCriteria toLevelSearchCriteria(LevelFilter l) {
        return LevelSearchCriteria.builder()
                .levelIds(l.getLevelIds())
                .gameIds(l.getGameIds())
                .name(l.getName())
                .price(l.getPrice())
                .hidden(l.getHidden())
                .build();
    }
}
