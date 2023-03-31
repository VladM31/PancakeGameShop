package nure.pancake.game.shop.authorizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.Role;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private AuthResponse.User user;
    private String tokenValue;
    private long tokenExpirationTime;

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
        private String birthDate;
        private String currency;
        private String email;
        private String role;
    }


}
