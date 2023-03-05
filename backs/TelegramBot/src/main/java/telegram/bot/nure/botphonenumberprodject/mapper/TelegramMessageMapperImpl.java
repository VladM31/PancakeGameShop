package telegram.bot.nure.botphonenumberprodject.mapper;

import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;
import telegram.bot.nure.botphonenumberprodject.dto.TelegramMessageRequest;

public class TelegramMessageMapperImpl implements TelegramMessageMapper{

    @Override
    public TelegramMessage toTelegramMessage(TelegramMessageRequest messageRequest) {
        return new TelegramMessage(
                messageRequest.getPhoneNumber(),
                messageRequest.getText(),
                System.currentTimeMillis(),
                messageRequest.getStyles());
    }
}
