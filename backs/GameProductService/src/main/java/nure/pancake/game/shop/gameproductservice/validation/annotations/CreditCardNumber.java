package nure.pancake.game.shop.gameproductservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import nure.pancake.game.shop.gameproductservice.validation.validators.CreditCardNumberValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CreditCardNumberValidator.class)
@Documented
public @interface CreditCardNumber {
    String message() default "Invalid Credit Card Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
