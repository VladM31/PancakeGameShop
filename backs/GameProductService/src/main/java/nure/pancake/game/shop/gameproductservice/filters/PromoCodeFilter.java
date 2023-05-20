package nure.pancake.game.shop.gameproductservice.filters;

import lombok.*;
import nure.pancake.game.shop.gameproductservice.utils.Range;

import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeFilter {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> promoIds;
    private Range<Integer> discountPercentage;
    private Range<LocalDate> endDate;
}
