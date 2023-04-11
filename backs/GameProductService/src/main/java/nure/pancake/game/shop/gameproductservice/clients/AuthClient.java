package nure.pancake.game.shop.gameproductservice.clients;


import nure.pancake.game.shop.gameproductservice.dataobjects.User;

import java.util.Optional;

public interface AuthClient {
    Optional<User> getUser(String token);
}
