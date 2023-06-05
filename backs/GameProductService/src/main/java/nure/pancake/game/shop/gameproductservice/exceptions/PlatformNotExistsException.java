package nure.pancake.game.shop.gameproductservice.exceptions;

public class PlatformNotExistsException extends RuntimeException {
    public PlatformNotExistsException() {
    }

    public PlatformNotExistsException(String message) {
        super(message);
    }

    public PlatformNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlatformNotExistsException(Throwable cause) {
        super(cause);
    }
}
