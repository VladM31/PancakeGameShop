package telegram.bot.nure.botphonenumberprodject.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TelegramSettingServiceImpl implements TelegramSettingService{
    private final TelegramEvents events;
    private final TelegramLongPollingCommandBot bot;
    private final InvokeCommandMethod commandMethod;

    @Override
    public void clearEvents(Long chatId) {
        var result = events.remove(chatId);
        String text = result == null ? "Event was empty" : "Event removed";

        try {
            bot.execute(SendMessage.builder().text(text)
                    .replyMarkup(new ReplyKeyboardRemove(true))
                    .chatId(chatId).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showAllCommand(Long chatId) {
        try {
            bot.execute(SendMessage.builder().chatId(chatId)
                    .text(
                            commandMethod
                                    .getCommands()
                                    .stream()
                                    .sorted()
                                    .collect(Collectors.joining("\n")))
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
