package nure.pancake.game.shop.gameproductservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "purchased_game")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "buy_date")
    private LocalDateTime buyDate;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "games_id")
    private Long gamesId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "purchasedGameId")
    private List<Level> levels;
}
