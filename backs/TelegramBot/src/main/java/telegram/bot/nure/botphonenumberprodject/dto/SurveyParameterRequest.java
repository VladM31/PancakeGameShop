package telegram.bot.nure.botphonenumberprodject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyParameterRequest {
    @Size(min = 1, max = 20, message = "Text length must be between 1 and 20", groups = OnValidate.class)
    @NotBlank(message = "Text mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Text mustn't be null", groups = OnValidate.class)
    private String text;
    @Size(min = 1, max = 100, message = "Url length must be between 1 and 100", groups = OnValidate.class)
    @NotBlank(message = "Data mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Data mustn't be null", groups = OnValidate.class)
    private String data;
}
