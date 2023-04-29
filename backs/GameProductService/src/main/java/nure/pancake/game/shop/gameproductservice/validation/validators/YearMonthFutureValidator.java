package nure.pancake.game.shop.gameproductservice.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nure.pancake.game.shop.gameproductservice.validation.annotations.YearMonthFuture;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class YearMonthFutureValidator implements ConstraintValidator<YearMonthFuture,String> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yy");
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() != 5){
            return false;
        }
        YearMonth yearMonth = YearMonth.parse(s, formatter);
        return YearMonth.now().isBefore(yearMonth);
    }
}
