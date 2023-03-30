package nure.pancake.game.shop.authorizationservice.clients;

import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.SurveyMessage;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.TextMessage;
import org.springframework.lang.NonNull;

public interface TelegramClient {
    boolean hasNumber(@NonNull String phoneNumber);

    boolean sendTextMessage(@NonNull TextMessage message);

    boolean sendSurveyMessage(@NonNull SurveyMessage message);
}
