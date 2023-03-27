package telegram.bot.nure.botphonenumberprodject.service;

import telegram.bot.nure.botphonenumberprodject.dataclass.SendStatus;
import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;

public interface MessageService {
    SendStatus sendMessage(TelegramMessage message);
}
