package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import nure.pancake.game.shop.authorizationservice.exceptions.FindParameterException;
import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import nure.pancake.game.shop.authorizationservice.filters.UserSortField;
import nure.pancake.game.shop.authorizationservice.filters.UserWebFilter;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class UserFilterMapperImpl implements UserFilterMapper {
    private final UserSortField defaultField = UserSortField.DATE_REGISTRATION;
    private final Map<UserSortField, String> fields = build();

    private Map<UserSortField, String> build() {
        return Map.ofEntries(
                entry(UserSortField.ID, "id"),
                entry(UserSortField.PHONE_NUMBER, "phoneNumber"),
                entry(UserSortField.NICKNAME, "nickname"),
                entry(UserSortField.FIRST_NAME, "firstName"),
                entry(UserSortField.LAST_NAME, "lastName"),
                entry(UserSortField.BIRTH_DATE, "birthDate"),
                entry(UserSortField.ACTIVE, "active"),
                entry(UserSortField.SELECTED_CURRENCY, "selectedCurrency"),
                entry(UserSortField.USER_STATE, "userState"),
                entry(UserSortField.EMAIL, "email"),
                entry(UserSortField.DATE_REGISTRATION, "dateRegistration"),
                entry(UserSortField.ROLE, "role")
        );
    }

    @Override
    public UserFilter toUserFiler(UserWebFilter f) {
        try {
            return UserFilter
                    .builder()
                    .userIds(f.getUserIds())
                    .selCurrencies(f.getSelCurrencies())
                    .phoneNumber(f.getPhoneNumber())
                    .nickname(f.getNickname())
                    .firstName(f.getFirstName())
                    .lastName(f.getLastName())
                    .email(f.getEmail())
                    .birthDate(f.getBirthDate())
                    .dateRegistration(f.getDateRegistration())
                    .active(f.getActive())
                    .nullableEmail(f.getNullableEmail())

                    .roles(f.getRoles() == null ? null :
                            f.getRoles().stream()
                                    .map(Role::valueOf).collect(Collectors.toSet()))

                    .userStates(f.getUserStates() == null ? null :
                            f.getUserStates().stream()
                                    .map(UserState::valueOf).collect(Collectors.toSet()))

                    .desc(f.isDesc())
                    .page(f.getPage())
                    .size(f.getSize())
                    .sortField(fields.get(toSortFiled(f)))

                    .build();
        } catch (IllegalArgumentException e) {
            throw new FindParameterException("Incorrect role or user state", e);
        }
    }

    @Override
    public UserSortField toSortFiled(UserWebFilter f) {
        return f.getSortField() == null ? defaultField : f.getSortField();
    }
}
