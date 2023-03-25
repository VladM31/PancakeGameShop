package telegram.bot.nure.botphonenumberprodject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telegram.bot.nure.botphonenumberprodject.dataclass.SendStatus;
import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private static final Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);
    private final UserService serviceUser;
    private final SendingWithDelay sendingWithDelay;
    private final MessageSender sender;

    public MessageServiceImpl(UserService serviceUser, SendingWithDelay sendingWithDelay, MessageSender sender) {
        this.serviceUser = serviceUser;
        this.sendingWithDelay = sendingWithDelay;
        this.sender = sender;
    }

    @Override
    public SendStatus sendMessage(TelegramMessage message) {
        try {
            var userOptional = getUser(message.getPhoneNumber());

            if (userOptional.isEmpty()) {
                sendingWithDelay.append(message);
                return SendStatus.DELAY_SENT;
            }

            sender.sendMessage(message, userOptional.get().getChatId());
            return SendStatus.SENT;
        } catch (RuntimeException e) {
            LOG.error("Error send message -> " + message, e);
            return SendStatus.ERROR;
        }
    }


    private Optional<User> getUser(String phoneNumber) {
        return serviceUser.findOne(
                UserUniqueFieldFilter.builder()
                        .phoneNumber(phoneNumber)
                        .build()
        );
    }
}
