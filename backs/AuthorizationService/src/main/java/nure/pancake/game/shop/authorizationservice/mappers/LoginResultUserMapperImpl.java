package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;

public class LoginResultUserMapperImpl implements LoginResultUserMapper {
    @Override
    public LoginResult.User toLoginUser(UserEntity e) {
        return LoginResult.User.builder()
                .id(e.getId())
                .phoneNumber(e.getPhoneNumber())
                .birthDate(e.getBirthDate())
                .email(e.getEmail())
                .firstName(e.getFirstName())
                .lastName(e.getLastName())
                .nickname(e.getNickname())
                .role(Role.valueOf(e.getRole().getName()))
                .selectedCurrency(e.getSelectedCurrency())
                .build();
    }
}
