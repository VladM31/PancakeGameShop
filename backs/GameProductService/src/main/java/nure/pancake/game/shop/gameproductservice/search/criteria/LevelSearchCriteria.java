package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelSearchCriteria implements Specification<LevelEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> levelIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Long> gameIds;
    private Range<Float> price;
    private String name;
    private Boolean hidden;

    @Override
    public Predicate toPredicate(Root<LevelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(levelIds)) {
            criteria.add(cb.in(root.get("id")).value(levelIds));
        }
        if (!CollectionUtils.isEmpty(gameIds)) {
            criteria.add(cb.in(root.get("gameId")).value(gameIds));
        }
        if (StringUtils.hasText(name)) {
            criteria.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (Range.hasFrom(price)) {
            criteria.add(
                    cb.ge(root.get("price"), price.fromMap(Float::parseFloat))
            );
        }
        if (Range.hasTo(price)) {
            criteria.add(
                    cb.le(root.get("price"), price.toMap(Float::parseFloat))
            );
        }
        if (hidden != null) {
            criteria.add(cb.equal(root.get("hidden"), hidden));
        }
        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<LevelEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
