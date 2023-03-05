package telegram.bot.nure.botphonenumberprodject.mapper;

import telegram.bot.nure.botphonenumberprodject.dto.HttpCallBackRequest;
import telegram.bot.nure.botphonenumberprodject.tools.HttpCallBack;

public interface HttpCallBackMapper {
    HttpCallBack toHttpCallBack(HttpCallBackRequest callBackRequest);
}
