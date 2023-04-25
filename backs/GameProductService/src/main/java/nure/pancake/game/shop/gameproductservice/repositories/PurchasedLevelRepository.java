package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface PurchasedLevelRepository extends
        Repository<PurchasedLevelEntity, Long>,
        JpaSpecificationExecutor<PurchasedLevelEntity>,
        ModifyRepository<PurchasedLevelEntity>{
}
