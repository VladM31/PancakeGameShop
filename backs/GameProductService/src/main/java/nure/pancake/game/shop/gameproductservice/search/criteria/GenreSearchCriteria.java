package nure.pancake.game.shop.gameproductservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
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
public class GenreSearchCriteria implements Specification<GenreEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Integer> genreIds;
    private Collection<String> names;
    private String name;

    @Override
    public Predicate toPredicate(Root<GenreEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(genreIds)) {
            criteria.add(cb.in(root.get("id")).value(genreIds));
        }
        if (!CollectionUtils.isEmpty(names)) {
            criteria.add(cb.in(root.get("name")).value(names));
        }
        if (StringUtils.hasText(name)) {
            criteria.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<GenreEntity>) null).toPredicate(root, query, cb);
        }

        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
