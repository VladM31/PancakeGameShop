package nure.pancake.game.shop.gameproductservice.entities.column;

import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;

import java.util.Map;

public enum LevelColumn {

    ID,
    NAME,
    DESCRIPTION,
    PRICE,
    MAIN_IMAGE,
    IMAGES,
    GAME_ID,
    HIDDEN;

    public static final ColumnProperty<LevelColumn, LevelEntity> PROPS = buildProp();

    private static ColumnProperty<LevelColumn, LevelEntity> buildProp() {
        return new ColumnProperty<>(
                LevelEntity.class,
                Map.of(
                        ID, LevelEntity::getId,
                        NAME, LevelEntity::getName,
                        DESCRIPTION, LevelEntity::getDescription,
                        PRICE, LevelEntity::getPrice,
                        MAIN_IMAGE, LevelEntity::getMainImage,
                        IMAGES, LevelEntity::getImages,
                        GAME_ID, LevelEntity::getGameId,
                        HIDDEN, LevelEntity::isHidden
                )
        );
    }
}
