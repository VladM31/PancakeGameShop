package telegram.bot.nure.botphonenumberprodject.tools;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface ContactReplyKeyboardBuilder {
    ReplyKeyboard build(String name);

    ReplyKeyboard build();
}
