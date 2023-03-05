package telegram.bot.nure.botphonenumberprodject.service;

import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;

public interface SendingWithDelay {
    void append(TelegramMessage message);
}
