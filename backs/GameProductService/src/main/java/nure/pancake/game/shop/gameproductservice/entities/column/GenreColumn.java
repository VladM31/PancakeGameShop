package nure.pancake.game.shop.gameproductservice.entities.column;


import lombok.Getter;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;

import java.util.Map;

public enum GenreColumn {
    ID,
    NAME;

    @Getter
    private static final ColumnProperty<GenreColumn, GenreEntity> PROPS = buildProp();

    private static ColumnProperty<GenreColumn, GenreEntity> buildProp() {
        return new ColumnProperty<>(
                GenreEntity.class,
                Map.of(
                        ID, GenreEntity::getId,
                        NAME, GenreEntity::getName
                )
        );
    }
}
