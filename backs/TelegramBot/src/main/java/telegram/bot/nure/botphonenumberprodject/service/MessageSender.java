package telegram.bot.nure.botphonenumberprodject.service;

import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;

import java.util.concurrent.CompletableFuture;

public interface MessageSender {
    CompletableFuture<Boolean> sendMessage(TelegramMessage message, Long chatId);
}
