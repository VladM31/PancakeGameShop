package nure.pancake.game.shop.authorizationservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.authorizationservice.entities.RoleEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static nure.pancake.game.shop.authorizationservice.utils.SearchCriteriaUtils.contains;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleSearchCriteria implements Specification<RoleEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Integer> roleIds;
    private Collection<String> names;
    private String name;

    @Override
    public Predicate toPredicate(Root<RoleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(roleIds)) {
            criteria.add(cb.in(root.get(RoleEntity.FieldName.ROLE_ID.getFieldName())).value(roleIds));
        }
        if (!CollectionUtils.isEmpty(names)) {
            criteria.add(cb.in(root.get(RoleEntity.FieldName.ROLE_NAME.getFieldName())).value(names));
        }
        if (StringUtils.hasText(name)) {
            criteria.add(contains(root, cb, name, RoleEntity.FieldName.ROLE_NAME));
        }

        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<RoleEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
