package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;

public interface GameMapper {
    Game toGame(GameEntity game);

    GameEntity toGameEntity(Game game);

}
