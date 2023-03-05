package telegram.bot.nure.botphonenumberprodject.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import telegram.bot.nure.botphonenumberprodject.dto.SurveyTelegramMessageRequest;
import telegram.bot.nure.botphonenumberprodject.dto.TelegramMessageRequest;
import telegram.bot.nure.botphonenumberprodject.mapper.SurveyTelegramMessageMapper;
import telegram.bot.nure.botphonenumberprodject.mapper.TelegramMessageMapper;
import telegram.bot.nure.botphonenumberprodject.service.MessageService;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidateForm;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/send-message")
@RequiredArgsConstructor
public class SendMessageController {
    private final MessageService messageService;
    private final TelegramMessageMapper messageMapper;
    private final SurveyTelegramMessageMapper surveyTelegramMessageMapper;

    @ResponseBody
    @Validated(OnValidateForm.class)
    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public boolean sendTextMessage(@RequestBody @Valid TelegramMessageRequest request) {
        return messageService.sendMessage(messageMapper.toTelegramMessage(request));
    }

    @ResponseBody
    @Validated(OnValidateForm.class)
    @RequestMapping(value = "/survey", method = RequestMethod.POST)
    public boolean sendSurveyMessage(@RequestBody @Valid SurveyTelegramMessageRequest request) {
        return messageService.sendMessage(surveyTelegramMessageMapper.toSurveyTelegramMessage(request));
    }
}
