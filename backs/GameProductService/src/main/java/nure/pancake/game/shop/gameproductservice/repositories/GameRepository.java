package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entity.Game;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface GameRepository extends Repository<Game, Long>, JpaSpecificationExecutor<Game> {
    Game save(@NonNull Game game);

    default Game update(@NonNull Game game) {
        return save(game);
    }
}