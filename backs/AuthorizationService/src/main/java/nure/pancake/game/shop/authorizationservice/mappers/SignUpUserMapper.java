package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;

public interface SignUpUserMapper<F> {
    SignUpUser toSignUpUser(F from);
}
