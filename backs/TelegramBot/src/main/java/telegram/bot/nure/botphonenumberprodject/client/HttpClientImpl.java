package telegram.bot.nure.botphonenumberprodject.client;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.apache.http.client.utils.URIBuilder;
import telegram.bot.nure.botphonenumberprodject.exception.HttpClientException;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class HttpClientImpl implements HttpClient{
    private final Gson gson;
    private final OkHttpClient client;

    @Override
    public String get(HttpRequestParameters requestParameters) {
        try {
            var url = Optional.ofNullable(requestParameters.getQuery())
                    .map(
                            q -> addQueryToUrl(q,requestParameters.getUrl())
                    ).orElse(requestParameters.getUrl());

            Request request = new Request.Builder()
                    .url(url)
                    .headers(Headers.of(requestParameters.getHeaders()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        } catch (Exception e) {
            throw new HttpClientException("Method get",e);
        }
    }

    @SneakyThrows
    private String addQueryToUrl(Map<String,String> query,String url){
        var urlBuilder = new URIBuilder(url);
        query.forEach(urlBuilder::addParameter);
        return urlBuilder.build().toString();
    }

    @Override
    public String post(HttpRequestParameters requestParameters) {
        MediaType type = requestParameters.buildMediaType();

        RequestBody body;

        if (type.toString().toLowerCase().contains("json")) {
            body = jsonBody(gson.toJson(requestParameters.getBody()), type);
        } else {
            body = anyType(requestParameters.getBody(), type);
        }

        Request request = new Request.Builder()
                .url(requestParameters.getUrl())
                .headers(Headers.of(requestParameters.getHeaders()))
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new HttpClientException("Method post",e);
        }
    }

    private RequestBody jsonBody(String json, MediaType type) {
        return RequestBody.create(json, type);
    }

    @SneakyThrows
    private RequestBody anyType(Map<? extends String, ?> params, MediaType type)  {
        var urlBuilder = new URIBuilder("");

        params.forEach((p, v) -> urlBuilder.addParameter(p, v.toString()));

        var paramsQuery = urlBuilder.build().getRawQuery();

        return RequestBody.create(paramsQuery, type);
    }
}
