package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;

public interface LoginResultUserMapper {
    LoginResult.User toLoginUser(UserEntity entity);
}
