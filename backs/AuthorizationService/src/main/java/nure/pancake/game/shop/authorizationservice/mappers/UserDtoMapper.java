package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.dto.UserList;

public interface UserDtoMapper {
    UserList.UserDto toUserDto(User user);
}
