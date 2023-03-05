package telegram.bot.nure.botphonenumberprodject.controller.telegram;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.stickers.StickerSet;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandController;
import telegram.bot.nure.botphonenumberprodject.annotations.BotCommandMapper;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.handle.InvokeCommandMethod;
import telegram.bot.nure.botphonenumberprodject.service.UserService;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@BotCommandController
public class CommandController {

    private final TelegramLongPollingCommandBot bot;

    public CommandController(TelegramLongPollingCommandBot bot) {
        this.bot = bot;
    }

    private final String TEXT = "Download file %s";
    @BotCommandMapper(commands = {"/download"})
    public void download(Update update) throws TelegramApiException, InterruptedException {
        final Long chat_id = update.getMessage().getChatId();

        final int messageId = bot.execute(SendMessage.builder().text(String.format(TEXT,0) + "%").chatId(chat_id.toString()).build()).getMessageId();

        final EditMessageText editMessageText = EditMessageText.builder().messageId(messageId).chatId(chat_id).text("").build();

        int process = 5;

        do {
            Thread.sleep(250L);
            editMessageText.setText(String.format(TEXT,process) + "%");
            process+=5;
            bot.execute(editMessageText);
        }while(process < 101);
        Thread.sleep(300L);
        editMessageText.setText("\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 Successful \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89");
        bot.execute(editMessageText);


        InputFile videoFile = new InputFile(new File("src/main/resources/video/good-good-evil-smile.gif"));

        SendAnimation animation = new SendAnimation(chat_id.toString(),videoFile);
        bot.executeAsync(animation);
    }

}
