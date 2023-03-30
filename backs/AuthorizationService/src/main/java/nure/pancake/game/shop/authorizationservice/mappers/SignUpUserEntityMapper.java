package nure.pancake.game.shop.authorizationservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.repositories.RoleRepository;
import nure.pancake.game.shop.authorizationservice.search.criteria.RoleSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class SignUpUserEntityMapper implements UserEntityMapper<SignUpUser> {
    private final RoleRepository roleRepository;

    @Override
    public UserEntity toUserEntity(SignUpUser u) {
        return entityBuilder()
                .phoneNumber(u.getPhoneNumber())
                .password(u.getPassword())
                .nickname(u.getNickname())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .birthDate(u.getBirthDate())
                .selectedCurrency(u.getCurrency())
                .email(u.getEmail())
                .build();
    }

    private UserEntity.UserEntityBuilder entityBuilder() {
        return UserEntity.builder()
                .userState(UserState.REGISTRATION)
                .active(true)
                .dateRegistration(LocalDateTime.now())
                .role(roleRepository.findOne(RoleSearchCriteria
                        .builder()
                        .names(List.of(Role.CUSTOMER.name()))
                        .build()).orElseThrow());
    }
}
