package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginUser;

public interface LoginUserMapper<F> {
    LoginUser toLoginUser(F from);
}
