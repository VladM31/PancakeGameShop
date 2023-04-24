package nure.pancake.game.shop.gameproductservice.convector;

import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.LevelSortFiled;
import nure.pancake.game.shop.gameproductservice.entities.column.LevelColumn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class LevelSortFiledConvector {
    private final Map<LevelSortFiled, String> mapper = build();

    public String convert(@NonNull LevelSortFiled filed) {
        return mapper.get(filed);
    }

    private Map<LevelSortFiled, String> build() {
        return Map.of(
                LevelSortFiled.ID, Objects.requireNonNull(LevelColumn.PROPS.getProp(LevelColumn.ID)),
                LevelSortFiled.NAME, Objects.requireNonNull(LevelColumn.PROPS.getProp(LevelColumn.NAME)),
                LevelSortFiled.PRICE, Objects.requireNonNull(LevelColumn.PROPS.getProp(LevelColumn.PRICE)),
                LevelSortFiled.GAME_ID, Objects.requireNonNull(LevelColumn.PROPS.getProp(LevelColumn.GAME_ID)),
                LevelSortFiled.HIDDEN, Objects.requireNonNull(LevelColumn.PROPS.getProp(LevelColumn.HIDDEN))
        );
    }
}
