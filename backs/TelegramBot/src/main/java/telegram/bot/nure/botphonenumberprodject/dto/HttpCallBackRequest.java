package telegram.bot.nure.botphonenumberprodject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telegram.bot.nure.botphonenumberprodject.validate.OnValidate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpCallBackRequest {
    @Size(min = 3, max = 400, message = "Url length must be between 3 and 400", groups = OnValidate.class)
    @NotBlank(message = "Url mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Url mustn't be null", groups = OnValidate.class)
    private String url;
    @NotNull(message = "Method mustn't be null", groups = OnValidate.class)
    private Method method;
    @Size(min = 1, message = "Parameter name length must be more than one", groups = OnValidate.class)
    @NotBlank(message = "Parameter name mustn't be blank", groups = OnValidate.class)
    @NotNull(message = "Parameter name mustn't be null", groups = OnValidate.class)
    private String parameterName;
    private Map<String,Object> body;
    private Map<String,String> headers;
}
