package nure.pancake.game.shop.gameproductservice.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nure.pancake.game.shop.gameproductservice.validation.annotations.ExistsPromoCode;
import org.springframework.util.StringUtils;

public class PromoCodeValidator implements ConstraintValidator<ExistsPromoCode,String> {
    private boolean canBlank;
    @Override
    public void initialize(ExistsPromoCode constraintAnnotation) {
        this.canBlank = constraintAnnotation.canBlank();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.hasText(s) && canBlank){
            return true;
        }

        return StringUtils.hasText(s) && s.length() > 10 && s.length() < 35;
    }
}
