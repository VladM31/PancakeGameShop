package telegram.bot.nure.botphonenumberprodject.mapper;

import org.springframework.web.bind.annotation.RequestMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import telegram.bot.nure.botphonenumberprodject.client.HttpRequestParameters;
import telegram.bot.nure.botphonenumberprodject.tools.HttpCallBack;

import java.util.Map;


public class HttpRequestParametersMapperImpl implements HttpRequestParametersMapper{

    @Override
    public HttpRequestParameters toHttpRequestParameters(HttpCallBack callBack, String data) {
        var builder = this.buildRequest(callBack);
        if(RequestMethod.GET.equals(callBack.getMethod())){
            return builder
                    .query(Map.of(callBack.getParameterName(), data))
                    .build();
        }
        callBack.getBody().put(callBack.getParameterName(), data);
        return builder
                .body(callBack.getBody())
                .build();
    }

    private HttpRequestParameters.HttpRequestParametersBuilder buildRequest(HttpCallBack callBack) {
        return HttpRequestParameters
                .builder()
                .url(callBack.getUrl())
                .headers(callBack.getHeaders());
    }
}
