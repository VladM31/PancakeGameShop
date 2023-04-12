package nure.pancake.game.shop.gameproductservice.filters;

import lombok.*;
import nure.pancake.game.shop.gameproductservice.utils.Range;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameLevelsFilter {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<String> genres;
    private Range<Float> price;
    private Range<Float> ageRating;
    private Range<LocalDate> releaseDate;
    private String name;
}
