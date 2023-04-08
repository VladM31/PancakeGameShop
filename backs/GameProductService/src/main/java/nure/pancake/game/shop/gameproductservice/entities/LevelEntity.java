package nure.pancake.game.shop.gameproductservice.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.ListStringConvector;

import java.util.List;

@Table(name = "levels")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "images")
    @Convert(converter = ListStringConvector.class)
    private List<String> images;
    @Column(name = "games_id")
    private Long gameId;
    @Column(name = "hidden")
    private boolean hidden;
}