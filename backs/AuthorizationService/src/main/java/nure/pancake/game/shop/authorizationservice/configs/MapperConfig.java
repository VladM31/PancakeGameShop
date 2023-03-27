package nure.pancake.game.shop.authorizationservice.configs;

import nure.pancake.game.shop.authorizationservice.mappers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    UserMapper userMapperImpl() {
        return new UserMapperImpl();
    }

    @Bean
    UserSearchCriteriaMapper UserSearchCriteriaMapperImpl() {
        return new UserSearchCriteriaMapperImpl();
    }

    @Bean
    UserFilterMapper userFilterMapperImpl() {
        return new UserFilterMapperImpl();
    }

    @Bean
    UserDtoMapper userDtoMapperImpl() {
        return new UserDtoMapperImpl();
    }
}
