package nure.pancake.game.shop.authorizationservice.search.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookedUser implements Specification<UserEntity> {
    private String email;
    private String phoneNumber;
    private long minutesWaitingForRegistration;
    @Singular(ignoreNullCollections = true)
    private List<Long> notUserIds;

    @Override
    public Predicate toPredicate(@NonNull Root<UserEntity> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        var phoneNumberIs = UserSearchCriteria.builder().phoneNumberEq(phoneNumber).build();
        var emailIs = UserSearchCriteria.builder().emailEq(email).build();

        var ifRegistered = UserSearchCriteria.builder().userState(UserState.REGISTERED).build();
        var ifRegistrationRecently = UserSearchCriteria.builder().userState(UserState.REGISTRATION)
                .dateRegistration(Range.ofFrom(LocalDateTime.now().minusMinutes(minutesWaitingForRegistration))).build();

        Specification<UserEntity> userIdsNotIn = CollectionUtils.isEmpty(notUserIds) ? Specification.where(null) :
                Specification.not(UserSearchCriteria.builder().userIds(notUserIds).build());

        return Specification.allOf(
                userIdsNotIn,
                phoneNumberIs.or(emailIs),
                Specification.anyOf(
                        ifRegistered,
                        ifRegistrationRecently
                )).toPredicate(root, query, criteriaBuilder);
    }
}
