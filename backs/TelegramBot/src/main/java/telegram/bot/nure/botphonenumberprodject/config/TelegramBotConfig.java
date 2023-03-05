package telegram.bot.nure.botphonenumberprodject.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram.bot.nure.botphonenumberprodject.bot.TelegramBot;
import telegram.bot.nure.botphonenumberprodject.handle.CommandHandle;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;
import telegram.bot.nure.botphonenumberprodject.handle.UserMessageHandle;
import telegram.bot.nure.botphonenumberprodject.handle.UserMessageHandleImpl;
import telegram.bot.nure.botphonenumberprodject.service.TelegramBotAuthorizationService;
import telegram.bot.nure.botphonenumberprodject.service.TelegramBotAuthorizationServiceImpl;
import telegram.bot.nure.botphonenumberprodject.service.UserService;
import telegram.bot.nure.botphonenumberprodject.tools.ContactReplyKeyboardBuilder;

@Configuration
@PropertySource("properties/BotResources.properties")
public class TelegramBotConfig {

    @Bean
    TelegramBotAuthorizationService telegramBotAuthorizationServiceImpl(
            UserService userService, ContactReplyKeyboardBuilder contactBuilder){
        return new TelegramBotAuthorizationServiceImpl(userService,contactBuilder);
    }

    @Bean
    UserMessageHandle userMessageHandleImpl(TelegramBotAuthorizationService authorizationService,
                                            CommandHandle commandHandle,
                                            TelegramEvents events){
        return new UserMessageHandleImpl(authorizationService,commandHandle,events);
    }

    @Bean
    TelegramLongPollingCommandBot telegramBot(@Value("${telegram.bot.username}") String username,
                                              @Value("${telegram.bot.token}") String token,
                                              UserMessageHandle messageHandle) {
        return new TelegramBot(username,token,messageHandle);
    }

    @Bean
    @SneakyThrows
    public TelegramBotsApi getTelegramBotsApi(TelegramLongPollingCommandBot bot) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
        return telegramBotsApi;
    }
}
