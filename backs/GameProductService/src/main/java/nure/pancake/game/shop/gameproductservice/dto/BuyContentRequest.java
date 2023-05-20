package nure.pancake.game.shop.gameproductservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.gameproductservice.validation.annotations.ExistsContent;
import nure.pancake.game.shop.gameproductservice.validation.annotations.ExistsPromoCode;
import nure.pancake.game.shop.gameproductservice.validation.annotations.NullableEmail;

import java.util.Set;

@Data
@Builder
@ExistsContent(message = "GameIds or levelIds are not exist")
@NoArgsConstructor
@AllArgsConstructor
public class BuyContentRequest {
    private Set<Long> gameIds;
    private Set<Long> levelIds;
    @NullableEmail
    private String email;
    @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15 and must be only numbers")
    private String phoneNumber;
    @ExistsPromoCode(canBlank = true)
    private String promoCode;
    @Valid
    private CreditCardRequest creditCard;
}
