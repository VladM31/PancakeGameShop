package telegram.bot.nure.botphonenumberprodject.tools;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UpdateUtils {
    public static Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        if (update.hasEditedMessage()) {
            return update.getEditedMessage().getChatId();
        }
        if (update.hasChannelPost()) {
            return update.getChannelPost().getChatId();
        }
        if (update.hasEditedChannelPost()) {
            return update.getEditedChannelPost().getChatId();
        }
        if (update.hasMyChatMember()) {
            return update.getMyChatMember().getChat().getId();
        }
        if (update.hasChatMember()) {
            return update.getChatMember().getChat().getId();
        }
        if (update.hasChatJoinRequest()) {
            return update.getChatJoinRequest().getChat().getId();
        }

        return null;
    }
}
