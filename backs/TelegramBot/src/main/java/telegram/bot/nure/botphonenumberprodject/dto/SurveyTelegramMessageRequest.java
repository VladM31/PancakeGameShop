package telegram.bot.nure.botphonenumberprodject.dto;

import lombok.*;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidate;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidateForm;
import telegram.bot.nure.botphonenumberprodject.validate.annotation.TelegramMessageValid;
import telegram.bot.nure.botphonenumberprodject.validate.annotation.TwoDimensionalArraySize;

import javax.validation.Valid;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TelegramMessageValid(checkGroups = OnValidate.class, groups = OnValidateForm.class, message = "Invalid survey telegram message")
public class SurveyTelegramMessageRequest extends TelegramMessageRequest{
    @TwoDimensionalArraySize(rowMin = 1, rowMax = 10,columnMin = 1, columnMax = 3, checkElements = true, groupsElements = OnValidate.class)
    private SurveyParameterRequest[][] parameterRequests;
    @Valid
    private HttpCallBackRequest callBack;
}
