package telegram.bot.nure.botphonenumberprodject.service;

import telegram.bot.nure.botphonenumberprodject.dataclass.TelegramMessage;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private final UserService serviceUser;
    private final SendingWithDelay sendingWithDelay;
    private final MessageSender sender;

    public MessageServiceImpl(UserService serviceUser, SendingWithDelay sendingWithDelay, MessageSender sender) {
        this.serviceUser = serviceUser;
        this.sendingWithDelay = sendingWithDelay;
        this.sender = sender;
    }

    @Override
    public boolean sendMessage(TelegramMessage message) {
        var userOptional = getUser(message.getPhoneNumber());

        if (userOptional.isEmpty()) {
            sendingWithDelay.append(message);
            return false;
        }

        sender.sendMessage(message, userOptional.get().getChatId());
        return true;
    }


    private Optional<User> getUser(String phoneNumber){
        return serviceUser.findOne(
                UserUniqueFieldFilter.builder()
                        .phoneNumber(phoneNumber)
                        .build()
        );
    }
}
