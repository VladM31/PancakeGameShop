package nure.pancake.game.shop.gameproductservice.filters;

import lombok.*;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.LevelSortFiled;
import nure.pancake.game.shop.gameproductservice.utils.Range;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LevelFilter extends FilterPage<LevelSortFiled> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    private Range<Float> price;
    private String name;
    private Boolean hidden;
}
