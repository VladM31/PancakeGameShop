package nure.pancake.game.shop.gameproductservice.validation.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import nure.pancake.game.shop.gameproductservice.validation.validators.ExistsContentValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ExistsContentValidator.class)
@Documented
public @interface ExistsContent {
    String message() default "Invalid Exists Content";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
