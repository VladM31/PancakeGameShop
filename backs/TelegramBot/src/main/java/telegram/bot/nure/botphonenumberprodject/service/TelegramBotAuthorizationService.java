package telegram.bot.nure.botphonenumberprodject.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramBotAuthorizationService {

    boolean isRegisteredUser(Update update);

    SendMessage signUp(Update update);
}
