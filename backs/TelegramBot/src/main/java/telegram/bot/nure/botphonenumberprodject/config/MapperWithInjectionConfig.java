package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import telegram.bot.nure.botphonenumberprodject.client.HttpClient;
import telegram.bot.nure.botphonenumberprodject.mapper.HttpCallBackMapper;
import telegram.bot.nure.botphonenumberprodject.mapper.HttpRequestParametersMapper;
import telegram.bot.nure.botphonenumberprodject.mapper.SurveyTelegramMessageMapper;
import telegram.bot.nure.botphonenumberprodject.mapper.SurveyTelegramMessageMapperImpl;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;

@Configuration
public class MapperWithInjectionConfig {

    @Bean
    SurveyTelegramMessageMapper surveyTelegramMessageMapperImpl(HttpCallBackMapper callBackMapper,
                                                                TelegramLongPollingCommandBot bot,
                                                                TelegramEvents events,
                                                                HttpClient httpClient,
                                                                HttpRequestParametersMapper requestParametersMapper) {
        return new SurveyTelegramMessageMapperImpl(callBackMapper, bot, events, httpClient,requestParametersMapper);
    }


}
