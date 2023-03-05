package telegram.bot.nure.botphonenumberprodject.handle;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;
import telegram.bot.nure.botphonenumberprodject.service.TelegramBotAuthorizationService;
import telegram.bot.nure.botphonenumberprodject.tools.UpdateUtils;

import java.util.Optional;

@RequiredArgsConstructor
public class UserMessageHandleImpl implements UserMessageHandle {
    private final TelegramBotAuthorizationService authorizationService;
    private final CommandHandle commandHandle;
    private final TelegramEvents events;

    @Override
    public Optional<SendMessage> handle(Update update) {
        if(!authorizationService.isRegisteredUser(update)){
            return Optional.of(authorizationService.signUp(update));
        }
        var mess = update.getMessage();

        if(mess != null && mess.isCommand()){
            commandHandle.invoke(update);
        }else{
            events.getOrDefault(UpdateUtils.getChatId(update), u -> null).apply(update);
        }

        return Optional.empty();
    }
}
