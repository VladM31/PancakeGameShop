package nure.pancake.game.shop.gameproductservice.exceptions;

public class PromoCodeException extends RuntimeException{
    public PromoCodeException() {
    }

    public PromoCodeException(String message) {
        super(message);
    }

    public PromoCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PromoCodeException(Throwable cause) {
        super(cause);
    }
}
