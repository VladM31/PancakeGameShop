package nure.pancake.game.shop.authorizationservice.configs;

import nure.pancake.game.shop.authorizationservice.dataobjects.LoginResult;
import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;
import nure.pancake.game.shop.authorizationservice.dto.LoginRequest;
import nure.pancake.game.shop.authorizationservice.dto.SignUpRequest;
import nure.pancake.game.shop.authorizationservice.mappers.*;
import nure.pancake.game.shop.authorizationservice.repositories.RoleRepository;
import nure.pancake.game.shop.authorizationservice.services.TokenService;
import nure.pancake.game.shop.authorizationservice.services.UserService;
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

    @Bean
    LoginResultUserMapper loginResultUserMapperImpl() {
        return new LoginResultUserMapperImpl();
    }

    @Bean
    UserEntityMapper<SignUpUser> signUpUserEntityMapper(RoleRepository roleRepository) {
        return new SignUpUserEntityMapper(roleRepository);
    }

    @Bean
    AuthResponseMapper<LoginResult> loginAuthResponseMapper() {
        return new LoginAuthResponseMapper();
    }

    @Bean
    LoginUserMapper<LoginRequest> reqLoginUserMapper() {
        return new ReqLoginUserMapper();
    }

    @Bean
    SignUpUserMapper<SignUpRequest> SignUpUserMapperReqImpl() {
        return new SignUpUserMapperReqImpl();
    }

    @Bean
    AuthResponseUserMapper<String> tokenAuthResponseUserMapper(UserService userService, TokenService tokenService) {
        return TokenAuthResponseUserMapper.builder()
                .userService(userService)
                .tokenService(tokenService)
                .build();
    }

    @Bean
    AuthResponseMapper<String> refreshTokenAuthResMap(
            AuthResponseUserMapper<String> userMapper,
            TokenService tokenService) {
        return RefreshTokenAuthResMap.builder()
                .userMapper(userMapper)
                .tokenService(tokenService)
                .build();
    }
}
