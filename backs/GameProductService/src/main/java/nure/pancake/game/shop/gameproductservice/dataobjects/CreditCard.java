package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    private String cardNumber;
    private LocalDate expiryDate;
    private String cardName;
    private String cvv2;
}
