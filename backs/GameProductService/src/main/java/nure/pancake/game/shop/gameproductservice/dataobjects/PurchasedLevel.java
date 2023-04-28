package nure.pancake.game.shop.gameproductservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedLevel {
    private Long id;
    private LocalDateTime buyDate;
    private Long levelsId;
    private Long purchasedGameId;
}
