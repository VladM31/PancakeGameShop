package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.filters.GameLevelsFilter;
import org.springframework.data.domain.Page;

public interface GameLevelsService {
    Page<GameLevels> findBy(GameLevelsFilter filter);
    boolean save(GameLevels gameLevels);
}
