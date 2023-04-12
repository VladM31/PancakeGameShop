package nure.pancake.game.shop.authorizationservice.controllers;

import jakarta.validation.ValidationException;
import nure.pancake.game.shop.authorizationservice.exceptions.AuthException;
import nure.pancake.game.shop.authorizationservice.exceptions.FindParameterException;
import nure.pancake.game.shop.authorizationservice.exceptions.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseBody
public class AdviceController {
    private final static Logger LOG = LoggerFactory.getLogger(AdviceController.class);

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {FindParameterException.class})
    public String handle(RuntimeException e) {
        LOG.warn(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public String handleValidationException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        return result.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {LoginException.class})
    public String handleAuthException(AuthException e) {
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class})
    public String handleValidationException(ValidationException e) {
        return e.getCause().getMessage();
    }
}
