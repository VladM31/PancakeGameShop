package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dto.AuthResponse;

public interface AuthResponseUserMapper<F> {
    AuthResponse.User toAuthResponseUser(F from);
}
