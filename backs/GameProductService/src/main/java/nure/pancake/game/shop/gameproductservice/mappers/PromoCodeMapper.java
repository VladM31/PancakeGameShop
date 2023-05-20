package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PromoCode;
import nure.pancake.game.shop.gameproductservice.entities.PromoCodeEntity;
import org.springframework.stereotype.Component;


@Component
public class PromoCodeMapper {

    public PromoCodeEntity toPromoCodeEntity(PromoCode code){
        return new PromoCodeEntity(code.id(),code.discountPercentage(),code.endDate());
    }
    public PromoCode toPromoCode(PromoCodeEntity code, String strCode){
        return new PromoCode(
                code.getId(),
                strCode,
                code.getDiscountPercentage(),
                code.getEndDate()
                );
    }
}
