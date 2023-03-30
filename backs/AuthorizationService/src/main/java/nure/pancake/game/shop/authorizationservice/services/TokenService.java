package nure.pancake.game.shop.authorizationservice.services;

import nure.pancake.game.shop.authorizationservice.dataobjects.Token;

import java.util.Optional;

public interface TokenService {
    Token generate(Token.User user);

    Optional<Token> refresh(String token);

    Optional<Token.User> toUser(String token);
}
