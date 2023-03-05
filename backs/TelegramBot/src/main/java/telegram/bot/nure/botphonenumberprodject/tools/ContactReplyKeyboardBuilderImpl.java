package telegram.bot.nure.botphonenumberprodject.tools;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ContactReplyKeyboardBuilderImpl implements ContactReplyKeyboardBuilder {
    public ReplyKeyboard build(String name){
        KeyboardRow row = new KeyboardRow();
        row.add(KeyboardButton.builder()
                .text(name)
                .requestContact(true)
                .build());

        return ReplyKeyboardMarkup
                .builder()
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .keyboard(List.of(row))
                .build();
    }

    public ReplyKeyboard build(){
        return this.build("Contact");
    }
}
