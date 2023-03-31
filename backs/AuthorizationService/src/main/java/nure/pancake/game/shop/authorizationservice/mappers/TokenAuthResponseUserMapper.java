package nure.pancake.game.shop.authorizationservice.mappers;

import jakarta.validation.ValidationException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.Token;
import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;
import nure.pancake.game.shop.authorizationservice.services.TokenService;
import nure.pancake.game.shop.authorizationservice.services.UserService;

@Builder
@RequiredArgsConstructor
public class TokenAuthResponseUserMapper implements AuthResponseUserMapper<String> {
    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public AuthResponse.User toAuthResponseUser(String token) {
        return tokenService.toUser(token.replaceAll("Bearer", "").trim())
                .map(Token.User::getId)
                .map(userService::findById)
                .orElseThrow(() -> new ValidationException("Token is not valid"))
                .map(this::toAuthResUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private AuthResponse.User toAuthResUser(User user) {
        return AuthResponse.User.builder()
                .role(user.getRole().name())
                .nickname(user.getNickname())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate().toString())
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .currency(user.getSelectedCurrency())
                .build();
    }


}
