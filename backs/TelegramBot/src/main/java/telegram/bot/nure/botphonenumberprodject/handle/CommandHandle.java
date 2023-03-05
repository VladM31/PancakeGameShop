package telegram.bot.nure.botphonenumberprodject.handle;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandle {
    Object invoke(Update update);
}
