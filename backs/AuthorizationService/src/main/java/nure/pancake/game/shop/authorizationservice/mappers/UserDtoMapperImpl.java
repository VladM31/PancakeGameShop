package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.dto.UserList;

public class UserDtoMapperImpl implements UserDtoMapper {
    @Override
    public UserList.UserDto toUserDto(User u) {
        return UserList
                .UserDto
                .builder()
                .id(u.getId())
                .phoneNumber(u.getPhoneNumber())
                .nickname(u.getNickname())
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .birthDate(u.getBirthDate())
                .active(u.getActive())
                .selectedCurrency(u.getSelectedCurrency())
                .userState(u.getUserState().name())
                .email(u.getEmail())
                .dateRegistration(u.getDateRegistration())
                .role(u.getRole().name())
                .build();
    }
}
