package telegram.bot.nure.botphonenumberprodject.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.events.EditUserEvent;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.tools.ContactReplyKeyboardBuilder;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;

import java.util.List;

@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final static String PHONE_NUMBER_TEXT = "Phone number -> ";

    private final TelegramLongPollingCommandBot bot;
    private final UserService userService;
    private final TelegramEvents events;
    private final ContactReplyKeyboardBuilder contactKeyboardBuilder;

    @Override
    public void editUser(Update update) {
        try {
            var chatId = UpdateUtils.getChatId(update);

            bot.execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text("Please share contact")
                            .replyMarkup(contactKeyboardBuilder.build()).build()
            );

            events.put(
                    chatId,
                    new EditUserEvent(bot,userService,events)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void showPhoneNumber(Update update) {
        var chatId = UpdateUtils.getChatId(update);

        var phoneNumber = userService
                .findOne(
                        UserUniqueFieldFilter
                                .builder()
                                .chatId(chatId)
                                .build()
                )
                .map(u -> u.getPhoneNumber())
                .orElse("not found");

        try {
            bot.execute(phoneNumberMessage(phoneNumber, chatId));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private SendMessage phoneNumberMessage(String phoneNumber, Long chatId) {
        return SendMessage
                .builder()
                .text(PHONE_NUMBER_TEXT + phoneNumber)
                .entities(boldStyle(phoneNumber.length()))
                .chatId(chatId)
                .build();
    }

    private List<MessageEntity> boldStyle(int textLength) {
        return List.of(
                MessageEntity
                        .builder()
                        .type("bold")
                        .offset(PHONE_NUMBER_TEXT.length())
                        .length(textLength)
                        .build());
    }
}
