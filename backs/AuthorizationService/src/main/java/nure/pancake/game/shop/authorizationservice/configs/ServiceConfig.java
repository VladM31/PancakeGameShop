package nure.pancake.game.shop.authorizationservice.configs;

import com.google.gson.Gson;
import nure.pancake.game.shop.authorizationservice.clients.TelegramClient;
import nure.pancake.game.shop.authorizationservice.dataobjects.SignUpUser;
import nure.pancake.game.shop.authorizationservice.mappers.LoginResultUserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserEntityMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserMapper;
import nure.pancake.game.shop.authorizationservice.mappers.UserSearchCriteriaMapper;
import nure.pancake.game.shop.authorizationservice.repositories.AuthCodeRepository;
import nure.pancake.game.shop.authorizationservice.repositories.UserRepository;
import nure.pancake.game.shop.authorizationservice.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;
import java.util.Random;

@Configuration
@PropertySource("classpath:properties/authorization.yml")
public class ServiceConfig {
    @Bean
    UserService userServiceImpl(
            UserMapper userMapper,
            UserSearchCriteriaMapper criteriaMapper,
            UserRepository repo) {
        return new UserServiceImpl(userMapper, criteriaMapper, repo);
    }

    @Bean
    TokenService jwtTokenService(
            @Value("${secret-word}") String secret,
            @Value("${plus-expiration-time}") long plusTime,
            @Value("${max-refresh-count}") int maxRefreshCount
    ) {
        return JwtTokenService
                .builder()
                .secretWord(secret)
                .plusExpirationTime(plusTime)
                .maxRefreshCount(maxRefreshCount)
                .gson(new Gson())
                .build();
    }

    @Bean
    CodeGenerator uuidCodeGenerator(
            @Value("${code-length}") int codeLength) {
        return UuidCodeGenerator
                .builder()
                .ran(new Random())
                .codeLength(codeLength)
                .build();
    }

    @Bean
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthService authServiceImpl(
            @Value("${confirm-url}") String confirmUrl,
            TelegramClient telegramClient,
            CodeGenerator codeGenerator,
            TokenService tokenService,
            UserRepository userRepository,
            AuthCodeRepository codeRepository,
            UserEntityMapper<SignUpUser> entityMapper,
            PasswordEncoder passwordEncoder,
            LoginResultUserMapper loginUserMapper
    ) {

        return AuthServiceImpl.builder()
                .entityMapper(entityMapper)
                .codeGenerator(codeGenerator)
                .confirmUrl(confirmUrl)
                .codeRepository(codeRepository)
                .tokenService(tokenService)
                .loginUserMapper(loginUserMapper)
                .passwordEncoder(passwordEncoder)
                .telegramClient(telegramClient)
                .userRepository(userRepository)
                .build();
    }
}
