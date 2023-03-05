package telegram.bot.nure.botphonenumberprodject.validate.annotation;

import telegram.bot.nure.botphonenumberprodject.validate.validation.TwoDimensionalArraySizeValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = TwoDimensionalArraySizeValidation.class)
public @interface TwoDimensionalArraySize {

    int rowMin() default 0;

    int rowMax() default Integer.MAX_VALUE;

    int columnMin() default 0;

    int columnMax() default Integer.MAX_VALUE;

    boolean nullable() default false;

    boolean checkElements() default false;

    Class<?>[] groupsElements() default {};

    String message() default "Invalid Two-Dimensional Array Size";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
