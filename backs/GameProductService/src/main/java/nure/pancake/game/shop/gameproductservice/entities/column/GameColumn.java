package nure.pancake.game.shop.gameproductservice.entities.column;

import nure.pancake.game.shop.gameproductservice.entities.GameEntity;

import java.util.Map;

public enum GameColumn {
    ID,
    NAME,
    ICON,
    PRICE,
    IMAGES,
    AGE_RATING,
    MAIN_IMAGE,
    DESCRIPTION,
    RELEASE_DATE,
    GENRE_ENTITIES;

    public static final ColumnProperty<GameColumn, GameEntity> PROPS = buildProp();

    private static ColumnProperty<GameColumn, GameEntity> buildProp() {
        return new ColumnProperty<>(
                GameEntity.class,
                Map.of(
                        ID, GameEntity::getId,
                        NAME, GameEntity::getName,
                        ICON, GameEntity::getIcon,
                        PRICE, GameEntity::getPrice,
                        IMAGES, GameEntity::getImages,
                        AGE_RATING, GameEntity::getAgeRating,
                        MAIN_IMAGE, GameEntity::getMainImage,
                        DESCRIPTION, GameEntity::getDescription,
                        RELEASE_DATE, GameEntity::getReleaseDate,
                        GENRE_ENTITIES, GameEntity::getGenreEntities
                )
        );
    }
}
