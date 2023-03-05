package telegram.bot.nure.botphonenumberprodject.exception;

public class HttpClientException extends RuntimeException{
    public HttpClientException() {
    }

    public HttpClientException(String message) {
        super(message);
    }

    public HttpClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
