package nure.pancake.game.shop.authorizationservice.configs;

import nure.pancake.game.shop.authorizationservice.mappers.UserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserSearchCriteriaMapper;
import nure.pancake.game.shop.authorizationservice.repositories.UserRepository;
import nure.pancake.game.shop.authorizationservice.services.UserService;
import nure.pancake.game.shop.authorizationservice.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    UserService userServiceImpl(
            UserMapper userMapper,
            UserSearchCriteriaMapper criteriaMapper,
            UserRepository repo) {
        return new UserServiceImpl(userMapper, criteriaMapper, repo);
    }
}
