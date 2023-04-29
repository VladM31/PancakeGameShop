package nure.pancake.game.shop.gameproductservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import nure.pancake.game.shop.gameproductservice.validation.validators.YearMonthFutureValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = YearMonthFutureValidator.class)
@Documented
public @interface YearMonthFuture {
    String message() default "Invalid Year Month Future";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
