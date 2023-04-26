package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

public interface GameService {
    Page<Game> findBy(GameFilter filter);

    boolean update(@NonNull Game game);

    boolean save(Game game);
}
