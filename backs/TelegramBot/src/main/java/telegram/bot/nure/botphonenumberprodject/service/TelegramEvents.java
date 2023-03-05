package telegram.bot.nure.botphonenumberprodject.service;

import org.jetbrains.annotations.NotNull;
import telegram.bot.nure.botphonenumberprodject.events.Event;

import java.util.function.Predicate;

public interface TelegramEvents {
    boolean put(@NotNull Long chatId, Event request);

    Event remove(Long chatId);

    boolean removeIf(Long chatId, Predicate<Event> predicate);

    Event get(Long chatId);

    Event getOrDefault(Long chatId, Event request);

}
