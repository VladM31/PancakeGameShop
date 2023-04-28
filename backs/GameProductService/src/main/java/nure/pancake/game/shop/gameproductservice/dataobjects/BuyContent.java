package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyContent {
    private Long userId;
    private Set<Long> gameIds;
    private Set<Long> levelIds;
    private CreditCard creditCard;
    private String email;
    private String phoneNumber;
}
