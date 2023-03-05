package telegram.bot.nure.botphonenumberprodject.controller.telegram;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandController;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandMapper;
import telegram.bot.nure.botphonenumberprodject.service.TelegramSettingService;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;

@BotCommandController
@RequiredArgsConstructor
public class SettingController {
    private final TelegramSettingService settingService;

    @BotCommandMapper(commands = "/clear")
    public void clear(Update update){
        settingService.clearEvents(UpdateUtils.getChatId(update));
    }

    @BotCommandMapper(commands = "/all")
    public void showAllCommands(Update update){
        settingService.showAllCommand(UpdateUtils.getChatId(update));
    }
}
