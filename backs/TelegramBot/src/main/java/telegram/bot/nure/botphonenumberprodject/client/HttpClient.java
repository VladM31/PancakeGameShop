package telegram.bot.nure.botphonenumberprodject.client;

public interface HttpClient {
    String get(HttpRequestParameters requestParameters);

    String post(HttpRequestParameters requestParameters);
}
