package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.*;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import nure.pancake.game.shop.gameproductservice.entities.column.PurchasedGameColumn;
import nure.pancake.game.shop.gameproductservice.entities.column.PurchasedLevelColumn;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static nure.pancake.game.shop.gameproductservice.entities.column.PurchasedGameColumn.BUY_DATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedGameSearchCriteria implements Specification<PurchasedGameEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purGameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> purLevelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> userIds;
    private Range<LocalDateTime> purGameBuyDate;

    @Override
    public Predicate toPredicate(Root<PurchasedGameEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        var t =root.get(PurchasedGameColumn.LEVELS.gfn());

        if (!CollectionUtils.isEmpty(purGameIds)) {
            criteria.add(cb.in(root.get(PurchasedGameColumn.ID.gfn())).value(purGameIds));
        }
        if (!CollectionUtils.isEmpty(gameIds)) {
            criteria.add(cb.in(root.get(PurchasedGameColumn.GAME_ID.gfn())).value(gameIds));
        }
        if (!CollectionUtils.isEmpty(userIds)) {
            criteria.add(cb.in(root.get(PurchasedGameColumn.USER_ID.gfn())).value(userIds));
        }
        if (!CollectionUtils.isEmpty(purLevelIds)) {
            criteria.add(cb.in(
                    levelsRoot(root)
                            .get(PurchasedLevelColumn.ID.gfn())
            ).value(purLevelIds));
        }
        if (!CollectionUtils.isEmpty(levelIds)) {
            criteria.add(cb.in(
                    levelsRoot(root)
                            .get(PurchasedLevelColumn.LEVEL_ID.gfn())
            ).value(levelIds));
        }
        if (Range.hasFrom(purGameBuyDate)) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(BUY_DATE.gfn()), purGameBuyDate.getFrom()));
        }
        if (Range.hasTo(purGameBuyDate)) {
            criteria.add(cb.lessThanOrEqualTo(root.get(BUY_DATE.gfn()), purGameBuyDate.getTo()));
        }


        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<PurchasedGameEntity>) null).toPredicate(root, query, cb);
        }

        return cb.and(criteria.toArray(Predicate[]::new));
    }

    private Path<?> levelsRoot(Root<PurchasedGameEntity> root){
        return  root.get(PurchasedGameColumn.LEVELS.gfn());
    }
}
