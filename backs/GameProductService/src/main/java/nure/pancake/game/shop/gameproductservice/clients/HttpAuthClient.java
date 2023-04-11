package nure.pancake.game.shop.gameproductservice.clients;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class HttpAuthClient implements AuthClient{
    private final RestTemplate restTemplate;
    private final String authHeader;
    private final String apiUrl;
    @Override
    public Optional<User> getUser(String token) {
        return sendRequest(token);
    }

    private MultiValueMap<String, String> buildHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(authHeader, token);
        return headers;
    }

    private Optional<User> sendRequest(String token) {
        try {
            var response = restTemplate.postForEntity(apiUrl,
                    new HttpEntity<>(buildHeader(token)), User.class);

            if (response.getStatusCode().isError()) {
                return Optional.empty();
            }

            return Optional.ofNullable(response.getBody());
        } catch (RuntimeException re) {
            return Optional.empty();
        }
    }
}
