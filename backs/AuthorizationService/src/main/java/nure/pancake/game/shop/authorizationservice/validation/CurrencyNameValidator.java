package nure.pancake.game.shop.authorizationservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyNameValidator implements ConstraintValidator<CurrencyName, String> {
    @Override
    public void initialize(CurrencyName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return value.matches("[a-zA-Z]+") && value.length() == 3;
    }
}