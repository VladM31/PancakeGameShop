package telegram.bot.nure.botphonenumberprodject.validate.validation;

import telegram.bot.nure.botphonenumberprodject.dto.TelegramMessageRequest;
import telegram.bot.nure.botphonenumberprodject.exception.ValidateException;
import telegram.bot.nure.botphonenumberprodject.validate.annotation.TelegramMessageValid;

import javax.validation.*;
import java.util.stream.Collectors;

public class TelegramMessageValidation implements ConstraintValidator<TelegramMessageValid, TelegramMessageRequest> {
    private final static String DELIMITER = " - ";
    private Class<?>[] checkGroups;
    private String message;
    private final Validator validator;

    public TelegramMessageValidation() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public void initialize(TelegramMessageValid annotation) {
        this.checkGroups = annotation.checkGroups();
        this.message = annotation.message();
    }

    @Override
    public boolean isValid(TelegramMessageRequest telegramMessage, ConstraintValidatorContext constraintValidatorContext) {
        var errors = this.validator.validate(telegramMessage, this.checkGroups)
                .stream()
                .collect(
                        Collectors.mapping(ConstraintViolation::getMessage,
                                Collectors.joining(DELIMITER)));

        if (errors.isBlank()) {
            return true;
        }

        throw new ValidateException(message + DELIMITER + errors);
    }
}
