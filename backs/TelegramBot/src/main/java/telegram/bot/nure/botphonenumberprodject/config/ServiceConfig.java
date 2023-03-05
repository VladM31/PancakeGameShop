package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import telegram.bot.nure.botphonenumberprodject.client.HttpClient;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethod;
import telegram.bot.nure.botphonenumberprodject.repository.UserRepository;
import telegram.bot.nure.botphonenumberprodject.service.*;
import telegram.bot.nure.botphonenumberprodject.tools.ContactReplyKeyboardBuilder;
import telegram.bot.nure.botphonenumberprodject.tools.ContactReplyKeyboardBuilderImpl;

@Configuration
@EnableScheduling
@EnableAsync
public class ServiceConfig {

    @Bean
    ContactReplyKeyboardBuilder contactReplyKeyboardBuilderImpl(){
        return new ContactReplyKeyboardBuilderImpl();
    }

    @Bean
    public UserService userService(UserRepository repository) {
        return new UserServiceImpl(repository);
    }

    @Bean
    public SendingWithDelay sendingWithDelayImpl(UserService userService, MessageSender sender) {
        return new SendingWithDelayImpl(userService, sender);
    }

    @Bean
    public MessageSender telegramMessageSender(TelegramLongPollingCommandBot bot,TelegramEvents events) {
        return new TelegramMessageSender(bot,events);
    }

    @Bean
    public MessageService messageServiceImpl(UserService serviceUser, SendingWithDelay sendingWithDelay, MessageSender sender) {
        return new MessageServiceImpl(serviceUser, sendingWithDelay, sender);
    }

    @Bean
    TelegramUserService telegramUserServiceImpl(TelegramLongPollingCommandBot bot,
                                                UserService userService,
                                                TelegramEvents events,
                                                ContactReplyKeyboardBuilder contact){
        return new TelegramUserServiceImpl(bot,userService,events,contact);
    }

    @Bean
    TelegramSettingService telegramSettingServiceImpl(TelegramEvents events,
                                                      TelegramLongPollingCommandBot bot,
                                                      InvokeCommandMethod commandMethod){
        return new TelegramSettingServiceImpl(events, bot, commandMethod);
    }
}
