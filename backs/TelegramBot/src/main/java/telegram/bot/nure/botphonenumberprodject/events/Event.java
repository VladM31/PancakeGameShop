package telegram.bot.nure.botphonenumberprodject.events;

import org.telegram.telegrambots.meta.api.objects.Message;
import telegram.bot.nure.botphonenumberprodject.handle.HandleRequest;

public interface Event extends HandleRequest {
    Object DEFAULT_RESULT = null;

    default Event setMessage(Message message){
        return this;
    }

    default boolean isExpired(){
        return false;
    }

    default void destroy(){
    }

    default boolean isDestroyed(){
        return false;
    }
}
