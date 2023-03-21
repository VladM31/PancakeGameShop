package nure.pancake.game.shop.gameproductservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "purchased_level")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "buy_date")
    private LocalDateTime buyDate;
    @Column(name = "levels_id")
    private Long levelsId;

    @Column(name = "purchased_game_id")
    private Long purchasedGameId;
}
