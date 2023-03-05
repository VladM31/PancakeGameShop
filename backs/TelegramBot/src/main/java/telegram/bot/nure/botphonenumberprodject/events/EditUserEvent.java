package telegram.bot.nure.botphonenumberprodject.events;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;
import telegram.bot.nure.botphonenumberprodject.service.UserService;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;


public record EditUserEvent(
        TelegramLongPollingCommandBot bot,
        UserService userService,
        TelegramEvents events
) implements Event {

    @Override
    public Object apply(Update update) {
        var chatId = UpdateUtils.getChatId(update);

        turnOffButton(chatId);

        if (update.getMessage() == null || update.getMessage().getContact() == null) {
            events.remove(chatId);
            return Event.DEFAULT_RESULT;
        }

        var user = userService.findOne(
                UserUniqueFieldFilter
                        .builder()
                        .chatId(chatId)
                        .build()
        ).orElseThrow();

        user.setPhoneNumber(update.getMessage().getContact().getPhoneNumber());

        userService.update(user);

        return Event.DEFAULT_RESULT;
    }

    public void turnOffButton(Long chatId) {
        try {
            bot.execute(SendMessage.builder()
                    .chatId(chatId)
                    .replyMarkup(new ReplyKeyboardRemove(true))
                    .text("Editing is done").build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
