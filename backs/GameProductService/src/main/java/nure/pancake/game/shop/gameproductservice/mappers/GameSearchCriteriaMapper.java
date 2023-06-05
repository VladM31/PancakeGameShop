package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Platforms;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameSearchCriteriaMapper {
    public GameSearchCriteria toGameSearchCriteria(GameFilter g) {
        return GameSearchCriteria.builder()
                .gameIds(g.getGameIds())
                .genreNames(g.getGenres())
                .price(g.getPrice())
                .ageRating(g.getAgeRating())
                .releaseDate(g.getReleaseDate())
                .name(g.getName())
                .platforms(
                        Optional
                                .ofNullable(g.getPlatforms())
                                .map(plars -> plars
                                        .stream()
                                        .map(Platforms::name)
                                        .toList())
                                .orElse(null))
                .build();
    }
}
