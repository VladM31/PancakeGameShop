package nure.pancake.game.shop.authorizationservice.mappers;

import jakarta.validation.ValidationException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;
import nure.pancake.game.shop.authorizationservice.services.TokenService;

@Builder
@RequiredArgsConstructor
public class RefreshTokenAuthResMap implements AuthResponseMapper<String> {
    private final AuthResponseUserMapper<String> userMapper;
    private final TokenService tokenService;

    @Override
    public AuthResponse toAuthResponse(String tokenValue) {
        var token = tokenService.refresh(tokenValue.replaceAll("Bearer", "").trim())
                .orElseThrow(() -> new ValidationException("Token is not correct"));
        var user = userMapper.toAuthResponseUser(token.getValue());

        return AuthResponse.builder()
                .tokenExpirationTime(token.getExpirationTime())
                .tokenValue(token.getValue())
                .user(user)
                .build();
    }
}
