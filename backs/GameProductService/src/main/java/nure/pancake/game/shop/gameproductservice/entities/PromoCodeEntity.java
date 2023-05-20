package nure.pancake.game.shop.gameproductservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "promo_code")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "discount_percentage")
    private int discountPercentage;
    @Column(name = "end_date")
    private LocalDateTime endDate;
}
