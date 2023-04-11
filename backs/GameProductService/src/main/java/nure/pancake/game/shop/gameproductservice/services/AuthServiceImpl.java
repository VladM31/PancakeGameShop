package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.clients.AuthClient;
import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;


import java.util.Optional;

@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthClient client;
    @Override
    @Cacheable(value = "authUsers",key = "#token", cacheManager = "authUserCacheManager")
    public Optional<User> getUser(@NonNull String token) {
        return client.getUser(token);
    }
}
