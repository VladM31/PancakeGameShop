package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface GameRepository extends Repository<GameEntity, Long>, JpaSpecificationExecutor<GameEntity> {
    GameEntity save(@NonNull GameEntity gameEntity);

    default GameEntity update(@NonNull GameEntity gameEntity) {
        return save(gameEntity);
    }
}