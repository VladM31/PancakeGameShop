package telegram.bot.nure.botphonenumberprodject.events;

import com.google.common.base.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegram.bot.nure.botphonenumberprodject.client.HttpClient;
import telegram.bot.nure.botphonenumberprodject.mapper.HttpRequestParametersMapper;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;
import telegram.bot.nure.botphonenumberprodject.tools.HttpCallBack;

@RequiredArgsConstructor
public class SurveyEvent implements Event {
    private final HttpCallBack callBack;
    private final TelegramLongPollingCommandBot bot;
    private final TelegramEvents events;
    private final HttpClient httpClient;
    private final HttpRequestParametersMapper requestParametersMapper;

    private Message message;
    private boolean destroyed;

    @Override
    public Object apply(Update update) {
        if (isSkip(update)) {
            return Event.DEFAULT_RESULT;
        }

        var httpRequestParameters = requestParametersMapper.toHttpRequestParameters(
                callBack,
                update.getCallbackQuery().getData()
        );

        if (RequestMethod.GET.equals(callBack.getMethod())) {
            httpClient.get(httpRequestParameters);
        } else {
            httpClient.post(httpRequestParameters);
        }

        completeSurvey();

        return Event.DEFAULT_RESULT;
    }

    private boolean isSkip(Update update){
        if (!update.hasCallbackQuery() ) {
            return true;
        }
        return !update.getCallbackQuery().getMessage().getMessageId().equals(message.getMessageId());
    }

    @Override
    public Event setMessage(Message message) {
        this.message = message;
        return this;
    }

    @Override
    public void destroy() {
        try {
            bot.execute(DeleteMessage
                    .builder()
                    .chatId(message.getChatId())
                    .messageId(message.getMessageId())
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } finally {
            destroyed = true;
        }
    }

    private void completeSurvey() {
        try {
            bot.execute(EditMessageText
                    .builder()
                    .chatId(message.getChatId())
                    .messageId(message.getMessageId())
                    .text(message.getText() + " ✅")
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        events.removeIf(message.getChatId(), this::equals);
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveyEvent that = (SurveyEvent) o;
        return Objects.equal(callBack, that.callBack) && Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(callBack, message);
    }
}
