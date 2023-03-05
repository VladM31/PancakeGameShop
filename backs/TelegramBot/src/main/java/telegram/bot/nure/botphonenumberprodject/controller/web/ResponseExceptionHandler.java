package telegram.bot.nure.botphonenumberprodject.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import telegram.bot.nure.botphonenumberprodject.exception.ValidateException;
import telegram.bot.nure.botphonenumberprodject.security.AuthenticationUser;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(@AuthenticationPrincipal AuthenticationUser user, ConstraintViolationException exception) {
        var errors = user.getName() + " : " + exception
                .getConstraintViolations()
                .stream()
                .collect(Collectors.mapping(
                        ConstraintViolation::getMessage,
                Collectors.joining(" - ")
        ));

        LOGGER.warn(errors);
        return errors;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidateException.class)
    public String handleValidateException(@AuthenticationPrincipal AuthenticationUser user, ValidateException exception) {
        var errors = user.getName() + " : " + exception.getMessage();
        LOGGER.warn(errors);
        return errors;
    }
}
