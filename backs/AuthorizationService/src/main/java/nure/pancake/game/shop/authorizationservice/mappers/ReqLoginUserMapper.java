package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginUser;
import nure.pancake.game.shop.authorizationservice.dto.LoginRequest;

public class ReqLoginUserMapper implements LoginUserMapper<LoginRequest> {
    @Override
    public LoginUser toLoginUser(LoginRequest f) {
        return new LoginUser(
                f.getPhoneNumber(),
                f.getPassword()
        );
    }
}
