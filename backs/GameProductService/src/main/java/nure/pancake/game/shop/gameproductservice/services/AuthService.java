package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface AuthService {
    Optional<User> getUser(@NonNull String token);
}
