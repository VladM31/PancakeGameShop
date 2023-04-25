package nure.pancake.game.shop.gameproductservice.entities.column;

import lombok.Getter;
import nure.pancake.game.shop.gameproductservice.entities.FieldNameGettable;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;

import java.util.Map;

public enum PurchasedGameColumn implements FieldNameGettable {
    ID,
    LEVELS,
    USER_ID,
    GAME_ID,
    BUY_DATE;

    @Getter
    private static final ColumnProperty<PurchasedGameColumn, PurchasedGameEntity> PROPS = buildProp();

    private static ColumnProperty<PurchasedGameColumn, PurchasedGameEntity> buildProp() {
        return new ColumnProperty<>(
                PurchasedGameEntity.class,
                Map.of(
                        ID, PurchasedGameEntity::getId,
                        LEVELS, PurchasedGameEntity::getLevels,
                        USER_ID, PurchasedGameEntity::getUserId,
                        GAME_ID, PurchasedGameEntity::getGamesId,
                        BUY_DATE ,PurchasedGameEntity::getBuyDate
                )
        );
    }

    @Override
    public String getFieldName() {
        return PurchasedGameColumn.PROPS.getProp(this);
    }
}
