package nure.pancake.game.shop.authorizationservice.services;

import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    Page<User> findBy(UserFilter filter);

    Optional<User> findById(Long id);
}
