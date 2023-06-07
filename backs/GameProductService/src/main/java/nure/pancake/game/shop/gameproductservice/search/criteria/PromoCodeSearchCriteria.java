package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.PromoCodeEntity;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromoCodeSearchCriteria implements Specification<PromoCodeEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> promoIds;
    private Range<Integer> discountPercentage;
    private Range<LocalDateTime> endDate;

    @Override
    public Predicate toPredicate(Root<PromoCodeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(promoIds)) {
            criteria.add(cb.in(root.get("id")).value(promoIds));
        }
        if (Range.hasFrom(discountPercentage)) {
            criteria.add(
                    cb.ge(root.get("discountPercentage"), discountPercentage.<Integer>fromMap(Integer::parseInt))
            );
        }
        if (Range.hasTo(discountPercentage)) {
            criteria.add(
                    cb.le(root.get("discountPercentage"), discountPercentage.<Integer>toMap(Integer::parseInt))
            );
        }
        if (Range.hasFrom(endDate)) {
            criteria.add(
                    cb.greaterThanOrEqualTo(root.get("endDate"), endDate.<LocalDateTime>fromMap(LocalDateTime::parse))
            );
        }
        if (Range.hasTo(endDate)) {
            criteria.add(
                    cb.lessThanOrEqualTo(root.get("endDate"), endDate.<LocalDateTime>toMap(LocalDateTime::parse))
            );
        }
        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<PromoCodeEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
