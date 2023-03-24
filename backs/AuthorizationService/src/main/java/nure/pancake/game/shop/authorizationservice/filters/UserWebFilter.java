package nure.pancake.game.shop.authorizationservice.filters;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nure.pancake.game.shop.authorizationservice.utils.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserWebFilter extends Page<UserSortField> {
    @Singular(ignoreNullCollections = true)
    private Set<Long> userIds;
    @Singular(ignoreNullCollections = true)
    private Set<String> roles;
    @Singular(ignoreNullCollections = true)
    private Set<String> selCurrencies;
    @Singular(ignoreNullCollections = true)
    private Set<String> userStates;
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
