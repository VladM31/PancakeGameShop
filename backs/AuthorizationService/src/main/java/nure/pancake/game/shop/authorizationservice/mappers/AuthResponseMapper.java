package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;

public interface AuthResponseMapper<F> {
    AuthResponse toAuthResponse(F from);
}
