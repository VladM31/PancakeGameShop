package telegram.bot.nure.botphonenumberprodject.validate.annotation;

import telegram.bot.nure.botphonenumberprodject.validate.validation.TelegramMessageValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE, ElementType.TYPE_PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = TelegramMessageValidation.class)
public @interface TelegramMessageValid {
    Class<?>[] checkGroups();

    String message() default "Invalid telegram message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
