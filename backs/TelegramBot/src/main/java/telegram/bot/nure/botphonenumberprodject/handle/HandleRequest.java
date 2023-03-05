package telegram.bot.nure.botphonenumberprodject.handle;

import org.telegram.telegrambots.meta.api.objects.Update;

@FunctionalInterface
public interface HandleRequest {
    Object apply(Update update);
}
