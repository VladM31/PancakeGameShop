package nure.pancake.game.shop.gameproductservice.convector;

import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GameSortFiled;
import nure.pancake.game.shop.gameproductservice.entities.column.GameColumn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class GameSortFiledConvector {
    private final Map<GameSortFiled,String> mapper = build();

    public String convert(@NonNull GameSortFiled filed){
        return mapper.get(filed);
    }

    private Map<GameSortFiled, String> build() {
        return Map.of(
                GameSortFiled.ID, Objects.requireNonNull(GameColumn.PROPS.getProp(GameColumn.ID)),
                GameSortFiled.NAME, Objects.requireNonNull(GameColumn.PROPS.getProp(GameColumn.NAME)),
                GameSortFiled.PRICE, Objects.requireNonNull(GameColumn.PROPS.getProp(GameColumn.PRICE)),
                GameSortFiled.AGE_RATING, Objects.requireNonNull(GameColumn.PROPS.getProp(GameColumn.AGE_RATING)),
                GameSortFiled.RELEASE_DATE, Objects.requireNonNull(GameColumn.PROPS.getProp(GameColumn.RELEASE_DATE))
        );
    }
}
