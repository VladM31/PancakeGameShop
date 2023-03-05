package telegram.bot.nure.botphonenumberprodject.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.dataclass.EventGetter;
import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class TelegramMessageSender implements MessageSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramMessageSender.class);
    private final TelegramLongPollingCommandBot bot;
    private final TelegramEvents events;

    @Override
    @Async
    public CompletableFuture<Boolean> sendMessage(TelegramMessage message, Long chatId) {
        try {
            var resultMessage = bot.execute(message.getSendMessageBuilder().chatId(chatId).build());

            if(message instanceof EventGetter){
                events.put(chatId, ((EventGetter) message).getEvent().setMessage(resultMessage));
            }

        } catch (TelegramApiException e) {
            LOGGER.error(e.getMessage(), e);
            return CompletableFuture.completedFuture(false);
        }
        return CompletableFuture.completedFuture(true);
    }
}
