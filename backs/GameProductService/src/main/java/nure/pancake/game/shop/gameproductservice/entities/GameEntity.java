package nure.pancake.game.shop.gameproductservice.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import nure.pancake.game.shop.gameproductservice.convector.ListStringConvector;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name = "games")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;
    @Column(name = "age_rating")
    private Integer ageRating;
    @Column(name = "release_date")
    private LocalDateTime releaseDate;
    @Column(name = "icon")
    private String icon;
    @Column(name = "main_image")
    private String mainImage;
    @Column(name = "video_link")
    private String videoLink;
    @Column(name = "platforms")
    @Convert(converter = ListStringConvector.class)
    private List<String> platforms;
    @Column(name = "images")
    @Convert(converter = ListStringConvector.class)
    private List<String> images;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "games_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    private Set<GenreEntity> genreEntities;

    @RequiredArgsConstructor
    public enum FieldName implements FieldNameGettable {
        GENRE("genreEntities");
        @Getter
        private final String fieldName;
    }
}
