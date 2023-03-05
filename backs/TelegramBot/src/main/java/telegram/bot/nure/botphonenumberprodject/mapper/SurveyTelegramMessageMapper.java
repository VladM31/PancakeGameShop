package telegram.bot.nure.botphonenumberprodject.mapper;

import telegram.bot.nure.botphonenumberprodject.dataclass.SurveyTelegramMessage;
import telegram.bot.nure.botphonenumberprodject.dto.SurveyTelegramMessageRequest;

public interface SurveyTelegramMessageMapper {
    SurveyTelegramMessage toSurveyTelegramMessage(SurveyTelegramMessageRequest request);
}
