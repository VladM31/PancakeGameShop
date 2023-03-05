package telegram.bot.nure.botphonenumberprodject.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import telegram.bot.nure.botphonenumberprodject.handle.UserMessageHandle;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingCommandBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramBot.class);
    private final String username;
    private final String token;
    private final UserMessageHandle messageHandle;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        try{
            var resultMessage = messageHandle.handle(update);

            if(resultMessage.isPresent()){
                execute(resultMessage.get());
            }


        }catch (Exception e){
            LOGGER.error("Error with execute telegram request ",e);
        }
    }



}
