package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.GenreFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.GenreSearchCriteria;
import org.springframework.stereotype.Component;

@Component
public class GenreSearchCriteriaMapper {
    public GenreSearchCriteria toGenreSearchCriteria(GenreFilter g) {
        return GenreSearchCriteria.builder()
                .names(g.getNames())
                .name(g.getName())
                .build();
    }
}
