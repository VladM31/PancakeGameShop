package nure.pancake.game.shop.gameproductservice.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import nure.pancake.game.shop.gameproductservice.dto.BuyContentRequest;
import nure.pancake.game.shop.gameproductservice.validation.annotations.ExistsContent;
import org.springframework.util.CollectionUtils;

public class ExistsContentValidator implements ConstraintValidator<ExistsContent, BuyContentRequest> {
    private String message;

    @Override
    public void initialize(ExistsContent constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(BuyContentRequest bcr, ConstraintValidatorContext constraintValidatorContext) {
        var res1 = CollectionUtils.isEmpty(bcr.getGameIds()) && CollectionUtils.isEmpty(bcr.getLevelIds());
        if(res1){
            throw new ValidationException(message);
        }
        return true;
    }
}
