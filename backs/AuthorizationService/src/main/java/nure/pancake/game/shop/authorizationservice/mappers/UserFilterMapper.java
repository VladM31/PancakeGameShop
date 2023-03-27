package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import nure.pancake.game.shop.authorizationservice.filters.UserSortField;
import nure.pancake.game.shop.authorizationservice.filters.UserWebFilter;

public interface UserFilterMapper {
    UserFilter toUserFiler(UserWebFilter f);

    UserSortField toSortFiled(UserWebFilter f);
}
