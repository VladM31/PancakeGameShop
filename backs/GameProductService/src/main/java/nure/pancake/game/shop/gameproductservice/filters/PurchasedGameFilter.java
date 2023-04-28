package nure.pancake.game.shop.gameproductservice.filters;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.PurchasedGameSortField;
import nure.pancake.game.shop.gameproductservice.utils.Range;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchasedGameFilter extends FilterPage<PurchasedGameSortField> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purGameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purLevelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> userIds;
    private Range<LocalDateTime> purGameBuyDate;
}
