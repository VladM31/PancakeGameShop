package telegram.bot.nure.botphonenumberprodject.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import telegram.bot.nure.botphonenumberprodject.beans.BotCommandMapperAndControllerBeanPostProcessor;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethod;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethodImpl;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEventsImpl;

@Configuration
public class BeanPostProcessorConfig {

    @Bean
    InvokeCommandMethod InvokeCommandMethodImpl() {
        return new InvokeCommandMethodImpl();
    }

    @Bean
    TelegramEvents messageEventsImpl() {
        return new TelegramEventsImpl();
    }

    @Bean
    BeanPostProcessor botCommandMapperAndControllerBeanPostProcessor(
            ApplicationContext context, InvokeCommandMethod commandMethod, Environment env) {
        return new BotCommandMapperAndControllerBeanPostProcessor(context, commandMethod, env);
    }
}
