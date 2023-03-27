package nure.pancake.game.shop.authorizationservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import nure.pancake.game.shop.authorizationservice.entities.RoleEntity;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity.FieldName;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static nure.pancake.game.shop.authorizationservice.utils.SearchCriteriaUtils.contains;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria implements Specification<UserEntity> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> userIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Integer> roleIds;
    @Singular(ignoreNullCollections = true)
    private Collection<String> roleNames;
    @Singular(ignoreNullCollections = true)
    private Collection<String> selCurrencies;
    @Singular(ignoreNullCollections = true)
    private Collection<UserState> userStates;
    private String phoneNumberEq;
    private String phoneNumber;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private String emailEq;
    private String passwordEq;
    private Range<LocalDate> birthDate;
    private Range<LocalDateTime> dateRegistration;
    private Boolean active;
    private Boolean nullableEmail;

    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> criteria = new ArrayList<>();

        if (!CollectionUtils.isEmpty(userIds)) {
            criteria.add(cb.in(root.get(FieldName.USER_ID.getFieldName())).value(userIds));
        }
        if (!CollectionUtils.isEmpty(roleIds)) {
            criteria.add(cb.in(root
                            .get(FieldName.ROLE.getFieldName())
                            .get(RoleEntity.FieldName.ROLE_ID.getFieldName()))
                    .value(roleIds));
        }
        if (!CollectionUtils.isEmpty(roleNames)) {
            criteria.add(cb.in(root
                            .get(FieldName.ROLE.getFieldName())
                            .get(RoleEntity.FieldName.ROLE_NAME.getFieldName()))
                    .value(roleNames));
        }
        if (!CollectionUtils.isEmpty(selCurrencies)) {
            criteria.add(cb.in(root.get(FieldName.SELECTED_CURRENCY.getFieldName())).value(selCurrencies));
        }
        if (!CollectionUtils.isEmpty(userStates)) {
            criteria.add(cb.in(root.get(FieldName.USER_STATE.getFieldName())).value(userStates));
        }

        if (StringUtils.hasText(phoneNumberEq)) {
            criteria.add(cb.equal(root.get(FieldName.PHONE_NUMBER.getFieldName()), phoneNumberEq));
        }
        if (StringUtils.hasText(emailEq)) {
            criteria.add(cb.equal(root.get(FieldName.EMAIL.getFieldName()), emailEq));
        }
        if (StringUtils.hasText(passwordEq)) {
            criteria.add(cb.equal(root.get(FieldName.PASSWORD.getFieldName()), passwordEq));
        }

        if (StringUtils.hasText(phoneNumber)) {
            criteria.add(contains(root, cb, phoneNumber, FieldName.PHONE_NUMBER));
        }
        if (StringUtils.hasText(nickname)) {
            criteria.add(contains(root, cb, nickname, FieldName.NICKNAME));
        }
        if (StringUtils.hasText(firstName)) {
            criteria.add(contains(root, cb, firstName, FieldName.FIRST_NAME));
        }
        if (StringUtils.hasText(lastName)) {
            criteria.add(contains(root, cb, lastName, FieldName.LAST_NAME));
        }
        if (StringUtils.hasText(email)) {
            criteria.add(contains(root, cb, email, FieldName.EMAIL));
        }

        if (Range.hasFrom(birthDate)) {
            criteria.add(cb.lessThanOrEqualTo(root.get(FieldName.BIRTH_DATE.getFieldName()), birthDate.getFrom()));
        }
        if (Range.hasTo(birthDate)) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(FieldName.BIRTH_DATE.getFieldName()), birthDate.getTo()));
        }
        if (Range.hasFrom(dateRegistration)) {
            criteria.add(cb.lessThanOrEqualTo(root.get(FieldName.DATE_REGISTRATION.getFieldName()), dateRegistration.getFrom()));
        }
        if (Range.hasTo(dateRegistration)) {
            criteria.add(cb.greaterThanOrEqualTo(root.get(FieldName.DATE_REGISTRATION.getFieldName()), dateRegistration.getTo()));
        }

        if (active != null) {
            criteria.add(cb.equal(root.get(FieldName.ACTIVE.getFieldName()), active));
        }
        if (nullableEmail != null) {
            var emailField = root.get(FieldName.EMAIL.getFieldName());
            criteria.add(nullableEmail ? cb.isNull(emailField) : cb.isNotNull(emailField));
        }

        if (CollectionUtils.isEmpty(criteria)) {
            return Specification.where((Specification<UserEntity>) null).toPredicate(root, query, cb);
        }
        return cb.and(criteria.toArray(Predicate[]::new));
    }
}
