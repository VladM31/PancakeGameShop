package nure.pancake.game.shop.gameproductservice.exceptions;

public class BuyContentException extends RuntimeException{
    public BuyContentException() {
    }

    public BuyContentException(String message) {
        super(message);
    }

    public BuyContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public BuyContentException(Throwable cause) {
        super(cause);
    }
}
