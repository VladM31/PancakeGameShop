package telegram.bot.nure.botphonenumberprodject.service;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.tools.ContactReplyKeyboardBuilder;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TelegramBotAuthorizationServiceImpl implements TelegramBotAuthorizationService{
    private final UserService userService;
    private final ContactReplyKeyboardBuilder contactButtonBuilder;

    @Override
    public boolean isRegisteredUser(Update update) {
        return userService.findOne(
                UserUniqueFieldFilter.builder()
                        .chatId(UpdateUtils.getChatId(update))
                        .build()
        ).isPresent();
    }

    @Override
    public SendMessage signUp(Update update) {
        if(update.getMessage().getContact() == null){
            return SendMessage.builder()
                    .chatId(update.getMessage().getChatId())
                    .text("Please share contact")
                    .replyMarkup(contactButtonBuilder.build()).build();
        }
        userService.save(new User(update.getMessage().getChatId(),
                update.getMessage().getContact().getPhoneNumber().replace("+", ""),
                LocalDateTime.now(), true));

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .replyMarkup(new ReplyKeyboardRemove(true))
                .text("Registration is successful").build();
    }
}
