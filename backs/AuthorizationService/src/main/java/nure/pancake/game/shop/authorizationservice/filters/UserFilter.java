package nure.pancake.game.shop.authorizationservice.filters;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import nure.pancake.game.shop.authorizationservice.utils.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserFilter extends Page<String> {
    @Singular(ignoreNullCollections = true)
    private Collection<Long> userIds;
    @Singular(ignoreNullCollections = true)
    private Collection<Role> roles;
    @Singular(ignoreNullCollections = true)
    private Collection<String> selCurrencies;
    @Singular(ignoreNullCollections = true)
    private Collection<UserState> userStates;
    private String phoneNumber;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private Range<LocalDate> birthDate;
    private Range<LocalDateTime> dateRegistration;
    private Boolean active;
    private Boolean nullableEmail;
}
