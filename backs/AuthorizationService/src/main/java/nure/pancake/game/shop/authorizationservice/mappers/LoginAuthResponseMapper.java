package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;

public class LoginAuthResponseMapper implements AuthResponseMapper<LoginResult> {
    @Override
    public AuthResponse toAuthResponse(LoginResult from) {
        return AuthResponse.builder()
                .tokenExpirationTime(from.getToken().getExpirationTime())
                .tokenValue(from.getToken().getValue())
                .user(toAuthResponseUser(from.getUser()))
                .build();
    }

    private AuthResponse.User toAuthResponseUser(LoginResult.User u) {
        return AuthResponse.User.builder()
                .id(u.getId())
                .currency(u.getSelectedCurrency())
                .phoneNumber(u.getPhoneNumber())
                .birthDate(u.getBirthDate().toString())
                .email(u.getEmail())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .nickname(u.getNickname())
                .role(u.getRole().name())
                .build();
    }
}
