package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import telegram.bot.nure.botphonenumberprodject.mapper.*;

@Configuration
public class MapperConfig {

    @Bean
    TelegramMessageMapper telegramMessageMapperImpl(){
        return new TelegramMessageMapperImpl();
    }

    @Bean
    HttpCallBackMapper httpCallBackMapperImpl(){
        return new HttpCallBackMapperImpl();
    }

    @Bean
    HttpRequestParametersMapper httpRequestParametersMapperImpl(){
        return new HttpRequestParametersMapperImpl();
    }
}
