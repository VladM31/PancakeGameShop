package nure.pancake.game.shop.gameproductservice.controllers;

import jakarta.validation.ValidationException;
import nure.pancake.game.shop.gameproductservice.exceptions.BuyContentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@ResponseBody
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorHandleController {
    private final Logger log = LoggerFactory.getLogger(ErrorHandleController.class);

    @ExceptionHandler(value = {ValidationException.class})
    public String handle(ValidationException e) {
        log.warn(e.getCause().getLocalizedMessage());
        return e.getCause().getLocalizedMessage();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public String handleValidationException(BindException ex) {
        String errors =  ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        log.warn(errors);
        return errors;
    }

    @ExceptionHandler({BuyContentException.class})
    public String handleBuyContentException(BuyContentException ex){
        log.warn(ex.getMessage());
        return ex.getMessage();
    }
}