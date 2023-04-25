package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static nure.pancake.game.shop.gameproductservice.entities.column.PurchasedLevelColumn.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedLevelSearchCriteria implements Specification<PurchasedLevelEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purLevelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purGameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    private Range<LocalDateTime> buyDate;

    @Override
    public Predicate toPredicate(Root<PurchasedLevelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(purLevelIds)) {
            criteria.add(cb.in(root.get(ID.gfn())).value(purLevelIds));
        }
        if (!CollectionUtils.isEmpty(purGameIds)) {
            criteria.add(cb.in(root.get(PURCHASED_GAME_ID.gfn())).value(purGameIds));
        }
        if (!CollectionUtils.isEmpty(levelIds)) {
            criteria.add(cb.in(root.get(LEVEL_ID.gfn())).value(levelIds));
        }
        if (Range.hasFrom(buyDate)) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(BUY_DATE.gfn()), buyDate.getFrom()));
        }
        if (Range.hasTo(buyDate)) {
            criteria.add(cb.lessThanOrEqualTo(root.get(BUY_DATE.gfn()), buyDate.getTo()));
        }

        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<PurchasedLevelEntity>) null).toPredicate(root, query, cb);
        }

        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
