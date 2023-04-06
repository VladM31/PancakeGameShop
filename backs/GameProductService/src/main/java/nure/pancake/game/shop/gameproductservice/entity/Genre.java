package nure.pancake.game.shop.gameproductservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "genres", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    @RequiredArgsConstructor
    public enum FieldName implements FieldNameGettable {
        GENRE_ID("id"),
        GENRE_NAME("name");

        @Getter
        private final String fieldName;
    }
}
