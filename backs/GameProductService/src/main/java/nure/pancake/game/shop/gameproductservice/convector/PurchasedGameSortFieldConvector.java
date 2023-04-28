package nure.pancake.game.shop.gameproductservice.convector;

import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.PurchasedGameSortField;
import nure.pancake.game.shop.gameproductservice.entities.column.PurchasedGameColumn;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PurchasedGameSortFieldConvector {
    private final Map<PurchasedGameSortField,String> map = build();

    private Map<PurchasedGameSortField, String> build() {
        return Map.of(
                PurchasedGameSortField.ID, PurchasedGameColumn.ID.gfn(),
                PurchasedGameSortField.USER_ID, PurchasedGameColumn.USER_ID.gfn(),
                PurchasedGameSortField.GAMES_ID, PurchasedGameColumn.GAME_ID.gfn(),
                PurchasedGameSortField.BUY_DATE, PurchasedGameColumn.BUY_DATE.gfn()
        );
    }

    public String convert(PurchasedGameSortField sortField){
        return map.get(sortField);
    }

}
