package telegram.bot.nure.botphonenumberprodject.handle;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface UserMessageHandle {
    Optional<SendMessage> handle(Update update);
}
