package telegram.bot.nure.botphonenumberprodject.mapper;

import telegram.bot.nure.botphonenumberprodject.client.HttpRequestParameters;
import telegram.bot.nure.botphonenumberprodject.tools.HttpCallBack;

public interface HttpRequestParametersMapper {
    HttpRequestParameters toHttpRequestParameters(HttpCallBack callBack,String data);
}
