package nure.pancake.game.shop.gameproductservice.dataobjects;

import java.time.LocalDateTime;

public record PromoCode(Long id,
                        String code,
                        int discountPercentage,
                        LocalDateTime endDate) {
}
