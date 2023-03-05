package telegram.bot.nure.botphonenumberprodject.dataclass;

import lombok.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import telegram.bot.nure.botphonenumberprodject.events.Event;

import java.util.List;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SurveyTelegramMessage extends TelegramMessage implements EventGetter{
    private Event event;
    private ReplyKeyboard keyboard;

    public SendMessage.SendMessageBuilder getSendMessageBuilder(){
        return super.getSendMessageBuilder().replyMarkup(keyboard);
    }

    public SurveyTelegramMessage(String phoneNumber, String text, long dateOfSend, List<MessageEntity> styles, Event event, ReplyKeyboard keyboard) {
        super(phoneNumber, text, dateOfSend, styles);
        this.event = event;
        this.keyboard = keyboard;
    }
}
