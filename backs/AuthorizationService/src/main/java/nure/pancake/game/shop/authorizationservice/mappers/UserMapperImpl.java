package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.Role;
import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.repositories.RoleRepository;
import nure.pancake.game.shop.authorizationservice.search.criteria.RoleSearchCriteria;

public class UserMapperImpl implements UserMapper {
    @Override
    public User toUser(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .nickname(entity.getNickname())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .active(entity.getActive())
                .selectedCurrency(entity.getSelectedCurrency())
                .userState(entity.getUserState())
                .email(entity.getEmail())
                .dateRegistration(entity.getDateRegistration())
                .role(Role.valueOf(entity.getRole().getName()))
                .build();
    }


}
