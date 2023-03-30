package nure.pancake.game.shop.authorizationservice.dataobjects.telegram;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class SurveyMessage extends TextMessage {
    @Singular(ignoreNullCollections = true)
    private List<List<TelegramButton>> parameterRequests;
    private TelegramCallBack callBack;
}
