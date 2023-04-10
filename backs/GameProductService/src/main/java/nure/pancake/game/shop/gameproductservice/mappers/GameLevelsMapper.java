package nure.pancake.game.shop.gameproductservice.mappers;


import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;

import java.util.List;

public interface GameLevelsMapper {

    GameLevels toGameLevels(GameEntity game, List<LevelEntity> levels);

}
