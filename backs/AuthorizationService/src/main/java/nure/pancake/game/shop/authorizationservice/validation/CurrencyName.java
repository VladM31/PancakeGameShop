package nure.pancake.game.shop.authorizationservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CurrencyNameValidator.class)
@Documented
public @interface CurrencyName {
    String message() default "Invalid currency name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}