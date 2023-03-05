package telegram.bot.nure.botphonenumberprodject.mapper;

import org.springframework.web.bind.annotation.RequestMethod;
import telegram.bot.nure.botphonenumberprodject.dto.HttpCallBackRequest;
import telegram.bot.nure.botphonenumberprodject.tools.HttpCallBack;

public class HttpCallBackMapperImpl implements HttpCallBackMapper{
    @Override
    public HttpCallBack toHttpCallBack(HttpCallBackRequest callBack) {
        return  new HttpCallBack(
                callBack.getUrl(),
                RequestMethod.valueOf(callBack.getMethod().name()),
                callBack.getBody(),
                callBack.getParameterName(),
                callBack.getHeaders());
    }
}
