package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.PromoCode;
import nure.pancake.game.shop.gameproductservice.filters.PromoCodeFilter;

import java.util.List;

public interface PromoCodeService {
    int getDiscountPercentage(String code);

    List<PromoCode> findAll(PromoCodeFilter filter);

    PromoCode save(PromoCode code);

    PromoCode update(PromoCode code);
}
