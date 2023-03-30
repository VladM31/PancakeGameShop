package nure.pancake.game.shop.authorizationservice.configs;

import nure.pancake.game.shop.authorizationservice.clients.HttpTelegramClient;
import nure.pancake.game.shop.authorizationservice.clients.TelegramClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:properties/telegram-api.yml")
public class ClientConfig {

    @Bean
    TelegramClient httpTelegramClient(
            @Value("${auth-header}") String authHeader,
            @Value("${token}") String token,
            @Value("${param-phone-number}") String paramPhoneNumber,
            @Value("${bot}") String urlBot,
            @Value("${has-phone-number}") String urlHasPhoneNumber,
            @Value("${text-message}") String urlSendTextMessage,
            @Value("${survey-message}") String urlSendSurveyMessage
    ) {
        return new HttpTelegramClient(authHeader, token,
                paramPhoneNumber, urlBot,
                urlHasPhoneNumber, urlSendTextMessage,
                urlSendSurveyMessage,
                new RestTemplate());
    }

}
