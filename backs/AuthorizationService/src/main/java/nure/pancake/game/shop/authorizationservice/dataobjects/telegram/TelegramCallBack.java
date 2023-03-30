package nure.pancake.game.shop.authorizationservice.dataobjects.telegram;


import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramCallBack {
    private String url;
    private HttpMethod method;
    private String parameterName;
    private Object body;
    private Map<String, String> headers;
}
