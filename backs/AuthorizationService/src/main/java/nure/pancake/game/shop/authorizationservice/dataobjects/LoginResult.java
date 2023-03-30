package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private LoginResult.User user;
    private Token token;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Long id;
        private String phoneNumber;
        private String nickname;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String selectedCurrency;
        private String email;
        private Role role;
    }


}
