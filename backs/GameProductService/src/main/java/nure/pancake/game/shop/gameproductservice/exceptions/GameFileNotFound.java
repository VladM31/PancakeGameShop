package nure.pancake.game.shop.gameproductservice.exceptions;

public class GameFileNotFound extends RuntimeException{
    public GameFileNotFound() {
    }

    public GameFileNotFound(String message) {
        super(message);
    }

    public GameFileNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public GameFileNotFound(Throwable cause) {
        super(cause);
    }
}
