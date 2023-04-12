package nure.pancake.game.shop.gameproductservice.filters;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GameSortFiled;
import nure.pancake.game.shop.gameproductservice.utils.Range;

import java.time.LocalDate;
import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GameLevelsFilter extends  FilterPage<GameSortFiled> {
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
