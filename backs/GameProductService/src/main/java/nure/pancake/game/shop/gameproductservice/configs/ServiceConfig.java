package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.clients.AuthClient;
import nure.pancake.game.shop.gameproductservice.services.AuthService;
import nure.pancake.game.shop.gameproductservice.services.AuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    AuthService authServiceImpl(AuthClient client) {
        return new AuthServiceImpl(client);
    }
}
