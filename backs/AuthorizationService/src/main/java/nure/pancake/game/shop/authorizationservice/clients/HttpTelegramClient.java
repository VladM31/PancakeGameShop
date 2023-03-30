package nure.pancake.game.shop.authorizationservice.clients;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.SurveyMessage;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.TelegramSendStatus;
import nure.pancake.game.shop.authorizationservice.dataobjects.telegram.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class HttpTelegramClient implements TelegramClient {
    private static final Logger LOG = LoggerFactory.getLogger(HttpTelegramClient.class);

    private final String authHeader;
    private final String token;
    private final String paramPhoneNumber;

    private final String urlBot;
    private final String urlHasPhoneNumber;
    private final String urlSendTextMessage;
    private final String urlSendSurveyMessage;
    private final RestTemplate client;

    @Override
    public boolean hasNumber(@NonNull String phoneNumber) {
        try {
            var result = client.exchange(new RequestEntity<>(buildHeader(), HttpMethod.GET,
                    buildUri(urlHasPhoneNumber, Map.of(paramPhoneNumber, phoneNumber))), Boolean.class);
            if (result.getStatusCode().isError()) {
                return false;
            }
            return Boolean.TRUE.equals(result.getBody());
        } catch (RuntimeException re) {
            LOG.error("Error by uri -> " + urlHasPhoneNumber, re);
            return false;
        }
    }

    @Override
    public boolean sendTextMessage(@NonNull TextMessage message) {
        return sendMessage(urlSendTextMessage, message);
    }

    @Override
    public boolean sendSurveyMessage(@NonNull SurveyMessage message) {
        return sendMessage(urlSendSurveyMessage, message);
    }

    private MultiValueMap<String, String> buildHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader, token);
        return headers;
    }

    private URI buildUri(String urlApi, Map<String, String> params) {
        var uriBuilder = UriComponentsBuilder.fromHttpUrl(urlBot + urlApi);

        for (Map.Entry<String, String> param : params.entrySet()) {
            uriBuilder.queryParam(param.getKey(), param.getValue());
        }

        return uriBuilder.build("");
    }

    private <M> boolean sendMessage(String urlSendMessage, M message) {
        return sendMessage(urlSendMessage, message, Collections.emptyMap());
    }

    private <M> boolean sendMessage(String urlSendMessage, M message, Map<String, String> query) {
        try {
            var response = client.postForEntity(buildUri(urlSendMessage, query),
                    new HttpEntity<>(message, buildHeader()), String.class);

            if (response.getStatusCode().isError()) {
                return false;
            }

            return !TelegramSendStatus.valueOf(response.getBody()).isError();
        } catch (RuntimeException re) {
            LOG.error("Error by url -> " + urlSendMessage, re);
            return false;
        }
    }
}
