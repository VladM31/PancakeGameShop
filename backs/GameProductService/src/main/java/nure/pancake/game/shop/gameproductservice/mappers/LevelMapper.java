package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;

public interface LevelMapper {
    Level toLevel(LevelEntity level);

    LevelEntity toLevelEntity(Level level);
}
