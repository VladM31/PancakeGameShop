package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entities.PromoCodeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;

public interface PromoCodeRepository extends Repository<PromoCodeEntity, Long>, JpaSpecificationExecutor<PromoCodeEntity> {
    PromoCodeEntity save(@NonNull PromoCodeEntity promoCodeEntity);

    default PromoCodeEntity update(@NonNull PromoCodeEntity promoCodeEntity) {
        return save(promoCodeEntity);
    }
}
