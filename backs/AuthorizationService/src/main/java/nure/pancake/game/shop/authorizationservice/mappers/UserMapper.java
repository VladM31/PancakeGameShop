package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;

public interface UserMapper {
    User toUser(UserEntity user);
}
