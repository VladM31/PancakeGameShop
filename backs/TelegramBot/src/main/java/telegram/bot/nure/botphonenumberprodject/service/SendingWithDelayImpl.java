package telegram.bot.nure.botphonenumberprodject.service;

import org.springframework.scheduling.annotation.Scheduled;
import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SendingWithDelayImpl implements SendingWithDelay {
    private static final long STORAGE_TIME_MINUTES = 30;
    private static final long BEFORE_START_SECONDS = 10 * 1000;
    private static final long DELAY_SECONDS = 25 * 1000;
    private static final Long NOT_FOUNT = null;

    private final Map<String, List<TelegramMessage>> messageStorage;
    private final UserService userService;
    private final MessageSender sender;

    public SendingWithDelayImpl(UserService userService, MessageSender sender) {
        this.userService = userService;
        this.sender = sender;
        messageStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void append(TelegramMessage message) {
        if (messageStorage.containsKey(message.getPhoneNumber())) {
            messageStorage.get(message.getPhoneNumber()).add(message);
            return;
        }

        messageStorage.put(message.getPhoneNumber(), new ArrayList<>());
        messageStorage.get(message.getPhoneNumber()).add(message);
        

    }

    @Scheduled(initialDelay = BEFORE_START_SECONDS, fixedDelay = DELAY_SECONDS)
    public void sendMessages() {
        if (messageStorage.isEmpty()) {
            return;
        }

        final long timeNow = System.currentTimeMillis();
        final Map<Long, List<TelegramMessage>> messages = new HashMap<>();

        String phoneNumber;

        for (Map.Entry<String, List<TelegramMessage>> entry : messageStorage.entrySet()) {
            phoneNumber = entry.getKey();
            if (clearStorageIfMessagesIsEmpty(phoneNumber, timeNow)) {
                continue;
            }

            Long chatId = getChatId(entry.getKey());

            if (chatId == NOT_FOUNT) {
                continue;
            }

            messages.put(chatId, messageStorage.remove(phoneNumber));
        }

        sendMessagesToChat(messages);
    }

    public Long getChatId(String phoneNumber) {
        return userService.findOne(
                        UserUniqueFieldFilter
                                .builder()
                                .phoneNumber(phoneNumber)
                                .build())
                .map(User::getChatId)
                .orElse(NOT_FOUNT);
    }

    private void sendMessagesToChat(Map<Long, List<TelegramMessage>> messages) {
        messages.forEach(
                (chatId, messageList) ->
                        messageList
                                .forEach(message ->
                                        sender.sendMessage(message, chatId)
                                )
        );
    }

    private boolean removeExpiredMessages(String phoneNumber, long timeNow) {
        return messageStorage.get(phoneNumber).removeIf(m -> timeNow - m.getDateOfSend() < STORAGE_TIME_MINUTES);
    }


    private boolean clearStorageIfMessagesIsEmpty(String phoneNumber, long timeNow) {
        removeExpiredMessages(phoneNumber, timeNow);
        if (messageStorage.get(phoneNumber).isEmpty()) {
            messageStorage.remove(phoneNumber);
            return true;
        }
        return false;
    }
}
