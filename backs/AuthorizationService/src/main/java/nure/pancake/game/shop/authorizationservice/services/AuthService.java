package nure.pancake.game.shop.authorizationservice.services;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.dataobjects.LoginUser;
import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;

public interface AuthService {
    boolean signUp(SignUpUser user);

    LoginResult login(LoginUser user);

    boolean isRegistered(String phoneNumber);

    boolean isBooked(String phoneNumber, String email);

    boolean confirmSignUp(String phoneNumber, String code);
}
