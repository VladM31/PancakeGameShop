package telegram.bot.nure.botphonenumberprodject.dataclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramMessage {
    private String phoneNumber;
    private String text;
    private long dateOfSend;
    private List<MessageEntity> styles;

    public SendMessage.SendMessageBuilder getSendMessageBuilder(){
        return SendMessage
                .builder()
                .text(text)
                .entities(styles);
    }
}
