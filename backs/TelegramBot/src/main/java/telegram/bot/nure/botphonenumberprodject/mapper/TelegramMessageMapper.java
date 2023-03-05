package telegram.bot.nure.botphonenumberprodject.mapper;

import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;
import telegram.bot.nure.botphonenumberprodject.dto.TelegramMessageRequest;

public interface TelegramMessageMapper {
    TelegramMessage toTelegramMessage(TelegramMessageRequest messageRequest);
}
