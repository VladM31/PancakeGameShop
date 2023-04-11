package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.clients.AuthClient;
import nure.pancake.game.shop.gameproductservice.clients.HttpAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;


@Configuration
@PropertySource("classpath:props/auth.client.yml")
public class ClientConfig {

    @Bean
    AuthClient httpAuthClient(
            @Value("${header}") String authHeader,
            @Value("${api-url}") String apiUrl
    ){
        return new HttpAuthClient(
                new RestTemplate(),
                authHeader,
                apiUrl
        );
    }
}
