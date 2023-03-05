package telegram.bot.nure.botphonenumberprodject.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequestParameters {
    private final static String JSON_TYPE = "application/json; charset=utf-8";
    private final static MediaType JSON_MEDIA = MediaType.get("application/json; charset=utf-8");

    private String url;
    private Map<String,String> query;
    private Map<String,Object> body;
    private Map<String,String> headers;

    public MediaType buildMediaType(){
        if(headers == null || !headers.containsKey("Content-Type")){
            return JSON_MEDIA;
        }
        return MediaType.get(headers.get("Content-Type"));
    }
}
