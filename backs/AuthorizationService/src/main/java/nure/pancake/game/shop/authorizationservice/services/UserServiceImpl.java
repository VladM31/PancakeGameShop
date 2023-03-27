package nure.pancake.game.shop.authorizationservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.User;
import nure.pancake.game.shop.authorizationservice.filters.UserFilter;
import nure.pancake.game.shop.authorizationservice.mappers.UserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserSearchCriteriaMapper;
import nure.pancake.game.shop.authorizationservice.repositories.UserRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserSearchCriteriaMapper criteriaMapper;
    private final UserRepository userRepository;

    @Override
    public Page<User> findBy(UserFilter f) {
        return userRepository.findAll(
                criteriaMapper.toUserSearchCriteria(f),
                criteriaMapper.toPageable(f)
        ).map(userMapper::toUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toUser);
    }


}
