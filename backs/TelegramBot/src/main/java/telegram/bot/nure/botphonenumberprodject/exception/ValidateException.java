package telegram.bot.nure.botphonenumberprodject.exception;

public class ValidateException extends RuntimeException{

    public ValidateException(String message){
        super(message);
    }

    public ValidateException(String message,Throwable throwable){
        super(message,throwable);
    }
}
