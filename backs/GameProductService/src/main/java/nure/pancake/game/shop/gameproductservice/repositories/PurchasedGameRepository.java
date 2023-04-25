package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface PurchasedGameRepository extends
        Repository<PurchasedGameEntity, Long>,
        JpaSpecificationExecutor<PurchasedGameEntity>,
        ModifyRepository<PurchasedGameEntity> {
}
