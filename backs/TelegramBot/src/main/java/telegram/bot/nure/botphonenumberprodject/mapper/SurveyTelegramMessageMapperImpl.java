package telegram.bot.nure.botphonenumberprodject.mapper;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegram.bot.nure.botphonenumberprodject.client.HttpClient;
import telegram.bot.nure.botphonenumberprodject.dataclass.SurveyTelegramMessage;
import telegram.bot.nure.botphonenumberprodject.dto.SurveyParameterRequest;
import telegram.bot.nure.botphonenumberprodject.dto.SurveyTelegramMessageRequest;
import telegram.bot.nure.botphonenumberprodject.events.Event;
import telegram.bot.nure.botphonenumberprodject.events.SurveyEvent;
import telegram.bot.nure.botphonenumberprodject.service.TelegramEvents;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SurveyTelegramMessageMapperImpl implements SurveyTelegramMessageMapper {
    private final HttpCallBackMapper callBackMapper;
    private final TelegramLongPollingCommandBot bot;
    private final TelegramEvents events;
    private final HttpClient httpClient;
    private final HttpRequestParametersMapper requestParametersMapper;

    @Override
    public SurveyTelegramMessage toSurveyTelegramMessage(SurveyTelegramMessageRequest request) {
        Event event = new SurveyEvent(
                callBackMapper.toHttpCallBack(request.getCallBack()),
                bot,
                events,
                httpClient,
                requestParametersMapper
        );

        return new SurveyTelegramMessage(
                request.getPhoneNumber(),
                request.getText(),
                System.currentTimeMillis(),
                request.getStyles(),
                event,
                toReplyKeyboard(request.getParameterRequests())
        );
    }

    private ReplyKeyboard toReplyKeyboard(SurveyParameterRequest[][] parameterRequests) {
        var buttons = Stream.of(parameterRequests)
                .map(params -> Stream
                        .of(params)
                        .map(this::toInlineKeyboardButton)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        return InlineKeyboardMarkup.builder().keyboard(buttons).build();
    }

    private InlineKeyboardButton toInlineKeyboardButton(SurveyParameterRequest request) {
        return InlineKeyboardButton.builder()
                .text(request.getText())
                .callbackData(request.getData())
                .build();
    }
}
