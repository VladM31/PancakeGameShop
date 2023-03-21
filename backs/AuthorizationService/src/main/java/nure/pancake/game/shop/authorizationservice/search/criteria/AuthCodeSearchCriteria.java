package nure.pancake.game.shop.authorizationservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.authorizationservice.entities.AuthCodeEntity;
import nure.pancake.game.shop.authorizationservice.entities.AuthCodeEntity.FieldName;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCodeSearchCriteria implements Specification<AuthCodeEntity> {
    private Collection<Long> codeIds;
    private Collection<Long> userIds;
    private String valueEq;
    private String userPhoneNumberEq;
    private Boolean active;
    private Range<LocalDateTime> dateOfCreation;

    @Override
    public Predicate toPredicate(Root<AuthCodeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(codeIds)) {
            criteria.add(cb.in(root.get(FieldName.CODE_ID.getFieldName())).value(codeIds));
        }
        if (!CollectionUtils.isEmpty(userIds)) {
            criteria.add(cb.in(root.get(FieldName.USER_ID.getFieldName())).value(userIds));
        }

        if (StringUtils.hasText(valueEq)) {
            criteria.add(cb.equal(root.get(FieldName.VALUE.getFieldName()), valueEq));
        }
        if (StringUtils.hasText(userPhoneNumberEq)) {
            Root<UserEntity> user = joinUser(criteria, root, query, cb);

            criteria.add(cb.equal(user.get(UserEntity.FieldName.PHONE_NUMBER.getFieldName()), userPhoneNumberEq));
        }
        if (active != null) {
            criteria.add(cb.equal(root.get(FieldName.ACTIVE.getFieldName()), active));
        }

        if (Range.hasFrom(dateOfCreation)) {
            criteria.add(cb.lessThanOrEqualTo(root.get(FieldName.DATE_OF_CREATION.getFieldName()), dateOfCreation.getFrom()));
        }
        if (Range.hasTo(dateOfCreation)) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(FieldName.DATE_OF_CREATION.getFieldName()), dateOfCreation.getTo()));
        }

        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<AuthCodeEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }

    private Root<UserEntity> joinUser(List<Predicate> criteria, Root<AuthCodeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Root<UserEntity> user = query.from(UserEntity.class);

        criteria.add(cb.equal(
                root.get(FieldName.USER_ID.getFieldName()),
                user.get(UserEntity.FieldName.USER_ID.getFieldName()))
        );

        return user;
    }
}
