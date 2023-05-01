package nure.pancake.game.shop.gameproductservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.gameproductservice.validation.annotations.CreditCardNumber;
import nure.pancake.game.shop.gameproductservice.validation.annotations.YearMonthFuture;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRequest {
    @CreditCardNumber(message = "Invalid card number")
    private String cardNumber;
    @YearMonthFuture(message = "Time is up")
    private String expiryDate;
    @NotBlank(message = "Card name mustn't be blank")
    private String cardName;
    @Pattern(regexp = "\\d{3}", message = "Invalid cvv2")
    private String cvv2;
}
