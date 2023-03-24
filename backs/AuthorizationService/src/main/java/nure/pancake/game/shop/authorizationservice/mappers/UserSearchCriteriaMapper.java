package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import nure.pancake.game.shop.authorizationservice.search.criteria.UserSearchCriteria;
import org.springframework.data.domain.Pageable;

public interface UserSearchCriteriaMapper {
    UserSearchCriteria toUserSearchCriteria(UserFilter filter);

    Pageable toPageable(UserFilter filter);
}
