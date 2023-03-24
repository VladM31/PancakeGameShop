package nure.pancake.game.shop.authorizationservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dto.UserList;
import nure.pancake.game.shop.authorizationservice.filters.UserWebFilter;
import nure.pancake.game.shop.authorizationservice.mappers.UserDtoMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserFilterMapper;
import nure.pancake.game.shop.authorizationservice.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFilterMapper filterMapper;
    private final UserDtoMapper dtoMapper;

    @GetMapping
    @ResponseBody
    public UserList users(UserWebFilter filter) {
        return new UserList(
                userService
                        .findBy(filterMapper.toUserFiler(filter))
                        .map(dtoMapper::toUserDto)
        ).sortColumn(filterMapper.toSortFiled(filter).name());
    }
}
