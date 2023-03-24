package nure.pancake.game.shop.authorizationservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "roles_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @RequiredArgsConstructor
    public enum FieldName implements FieldNameGettable {
        ROLE_ID("id"),
        ROLE_NAME("name");

        @Getter
        private final String fieldName;
    }
}
