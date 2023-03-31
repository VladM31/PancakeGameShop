package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;
import nure.pancake.game.shop.authorizationservice.dto.SignUpRequest;

import java.util.Locale;

public class SignUpUserMapperReqImpl implements SignUpUserMapper<SignUpRequest> {
    @Override
    public SignUpUser toSignUpUser(SignUpRequest req) {
        return SignUpUser
                .builder()
                .nickname(req.getNickname())
                .birthDate(req.getBirthDate())
                .lastName(req.getLastName())
                .firstName(req.getFirstName())
                .email(req.getEmail())
                .currency(req.getCurrency().toUpperCase(Locale.ROOT))
                .password(req.getPassword())
                .phoneNumber(req.getPhoneNumber())
                .build();
    }
}
