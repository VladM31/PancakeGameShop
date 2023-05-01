package nure.pancake.game.shop.gameproductservice.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nure.pancake.game.shop.gameproductservice.validation.annotations.CreditCardNumber;


public class CreditCardNumberValidator implements ConstraintValidator<CreditCardNumber, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        // Removing all non-numeric characters from the card number
        String cardNumber = s.replaceAll("[^\\d]", "");

        // We check that the length of the card number is from 13 to 19 characters
        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }

        // Checking that the card number passes the Luhn algorithm
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
