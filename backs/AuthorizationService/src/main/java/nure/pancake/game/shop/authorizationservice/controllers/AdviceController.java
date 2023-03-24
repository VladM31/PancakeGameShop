package nure.pancake.game.shop.authorizationservice.controllers;

import nure.pancake.game.shop.authorizationservice.exceptions.FindParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {
    private final static Logger LOG = LoggerFactory.getLogger(AdviceController.class);

    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = FindParameterException.class)
    public String handle(FindParameterException e) {
        LOG.warn(e.getMessage());
        return e.getMessage();
    }
}
