package telegram.bot.nure.botphonenumberprodject.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpCallBack {
    private String url;
    private RequestMethod method;
    private Map<String,Object> body;
    private String parameterName;
    private Map<String,String> headers;
}
