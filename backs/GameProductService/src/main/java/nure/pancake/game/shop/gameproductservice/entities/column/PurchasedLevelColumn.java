package nure.pancake.game.shop.gameproductservice.entities.column;

import lombok.Getter;
import nure.pancake.game.shop.gameproductservice.entities.FieldNameGettable;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;

import java.util.Map;

public enum PurchasedLevelColumn implements FieldNameGettable {
    ID,
    BUY_DATE,
    LEVEL_ID,
    PURCHASED_GAME_ID;

    @Getter
    private static final ColumnProperty<PurchasedLevelColumn, PurchasedLevelEntity> PROPS = buildProp();

    private static ColumnProperty<PurchasedLevelColumn, PurchasedLevelEntity> buildProp() {
        return new ColumnProperty<>(
                PurchasedLevelEntity.class,
                Map.of(
                        ID, PurchasedLevelEntity::getId,
                        BUY_DATE, PurchasedLevelEntity::getBuyDate,
                        LEVEL_ID, PurchasedLevelEntity::getLevelsId,
                        PURCHASED_GAME_ID, PurchasedLevelEntity::getPurchasedGameId
                )
        );
    }

    @Override
    public String getFieldName() {
        return PurchasedLevelColumn.PROPS.getProp(this);
    }

}
