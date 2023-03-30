package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.HttpMethod;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.SurveyMessage;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.TelegramButton;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.TelegramCallBack;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmMessage {
    private String phoneNumber;
    private String code;
    private String callBackUrl;

    public SurveyMessage toMessage() {
        return SurveyMessage
                .builder()
                .phoneNumber(phoneNumber)
                .text("Click on button that confirm sign up")
                .parameterRequest(List.of(
                        TelegramButton.builder().text("Confirm").data(code).build()
                ))
                .callBack(
                        TelegramCallBack
                                .builder()
                                .url(UriComponentsBuilder.fromHttpUrl(callBackUrl)
                                        .queryParam("phoneNumber", phoneNumber)
                                        .build("").toString())
                                .headers(Collections.emptyMap())
                                .parameterName("code")
                                .method(HttpMethod.GET)
                                .build()
                )
                .build();
    }
}
