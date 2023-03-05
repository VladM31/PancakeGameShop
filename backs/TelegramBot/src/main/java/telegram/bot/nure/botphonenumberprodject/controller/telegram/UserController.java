package telegram.bot.nure.botphonenumberprodject.controller.telegram;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandController;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandMapper;
import telegram.bot.nure.botphonenumberprodject.service.TelegramUserService;

@BotCommandController
@RequiredArgsConstructor
public class UserController {
    private final TelegramUserService telegramUserService;

    @BotCommandMapper(commands = {"/edit"})
    public void edit(Update update){
        telegramUserService.editUser(update);
    }

    @BotCommandMapper(commands = {"/show_phone_number"})
    public void show(Update update) {
        telegramUserService.showPhoneNumber(update);
    }
}
