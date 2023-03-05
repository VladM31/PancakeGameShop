package telegram.bot.nure.botphonenumberprodject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidate;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidateForm;
import telegram.bot.nure.botphonenumberprodject.validate.annotation.TelegramMessageValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TelegramMessageValid(checkGroups = OnValidate.class, groups = OnValidateForm.class, message = "Invalid telegram text message")
public class TelegramMessageRequest {
    @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15", groups = OnValidate.class)
    @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15", groups = OnValidate.class)
    @NotBlank(message = "Phone number mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Phone number mustn't be null", groups = OnValidate.class)
    private String phoneNumber;
    @Size(min = 1, max = 1000, message = "Text length must be between 1 and 1000", groups = OnValidate.class)
    @NotBlank(message = "Text mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Text mustn't be null", groups = OnValidate.class)
    private String text;

    private List<MessageEntity> styles;
}
