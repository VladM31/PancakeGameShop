package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import nure.pancake.game.shop.authorizationservice.search.criteria.UserSearchCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Map;

import static java.util.Map.entry;
import static nure.pancake.game.shop.authorizationservice.entities.UserEntity.FieldName;

public class UserSearchCriteriaMapperImpl implements UserSearchCriteriaMapper {
    private final String defaultField = "dateRegistration";
    private final Map<String, String> fields = build();

    private Map<String, String> build() {
        return Map.ofEntries(
                entry("id", FieldName.USER_ID.getFieldName()),
                entry("phoneNumber", FieldName.PHONE_NUMBER.getFieldName()),
                entry("nickname", FieldName.NICKNAME.getFieldName()),
                entry("firstName", FieldName.FIRST_NAME.getFieldName()),
                entry("lastName", FieldName.LAST_NAME.getFieldName()),
                entry("birthDate", FieldName.BIRTH_DATE.getFieldName()),
                entry("active", FieldName.ACTIVE.getFieldName()),
                entry("selectedCurrency", FieldName.SELECTED_CURRENCY.getFieldName()),
                entry("userState", FieldName.USER_STATE.getFieldName()),
                entry("email", FieldName.EMAIL.getFieldName()),
                entry(defaultField, FieldName.DATE_REGISTRATION.getFieldName()),
                entry("role", FieldName.ROLE.getFieldName())
        );
    }

    @Override
    public UserSearchCriteria toUserSearchCriteria(UserFilter f) {
        return UserSearchCriteria
                .builder()
                .userIds(f.getUserIds())
                .roleNames(f.getRoles() == null ? null : f.getRoles().stream().map(Enum::name).toList())
                .selCurrencies(f.getSelCurrencies())
                .userStates(f.getUserStates())
                .phoneNumber(f.getPhoneNumber())
                .nickname(f.getNickname())
                .firstName(f.getFirstName())
                .lastName(f.getLastName())
                .email(f.getEmail())
                .birthDate(f.getBirthDate())
                .dateRegistration(f.getDateRegistration())
                .active(f.getActive())
                .nullableEmail(f.getNullableEmail())
                .build();
    }

    @Override
    public Pageable toPageable(UserFilter f) {
        return f.toPageable(0, 20,
                fields.get(
                        f.getSortField() != null ?
                                f.getSortField() :
                                defaultField));
    }
}
