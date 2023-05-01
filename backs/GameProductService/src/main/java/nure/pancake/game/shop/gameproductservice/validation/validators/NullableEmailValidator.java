package nure.pancake.game.shop.gameproductservice.validation.validators;

import jakarta.validation.*;
import jakarta.validation.constraints.Email;
import nure.pancake.game.shop.gameproductservice.validation.annotations.NullableEmail;

import java.util.Objects;
import java.util.stream.Collectors;

public class NullableEmailValidator implements ConstraintValidator<NullableEmail, String> {
    private final Validator validator;
    private int min;
    private int max;

    public NullableEmailValidator() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public void initialize(NullableEmail constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return true;
        }
        this.validLength(s);
        this.validEmail(s);
        return true;
    }

    private void validLength(String email) {
        var length = email.trim().length();
        if (min <= length && length <= max) {
            return;
        }
        throw new ValidationException("Email must be between %d and %d".formatted(min, max));
    }

    private void validEmail(@Email String email) {
        var errors = validator.validate(email)
                .stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
