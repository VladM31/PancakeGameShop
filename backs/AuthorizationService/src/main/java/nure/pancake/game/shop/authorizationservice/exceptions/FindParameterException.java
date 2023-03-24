package nure.pancake.game.shop.authorizationservice.exceptions;

public class FindParameterException extends RuntimeException {
    public FindParameterException() {
    }

    public FindParameterException(String message) {
        super(message);
    }

    public FindParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
