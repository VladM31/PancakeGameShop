package nure.pancake.game.shop.authorizationservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "authentication_code")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "dateOfCreation")
public class AuthCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDateTime dateOfCreation;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "users_table_id", nullable = false)
    private Long userId;

    @RequiredArgsConstructor
    public enum FieldName implements FieldNameGettable {
        CODE_ID("id"),
        VALUE("value"),
        ACTIVE("active"),
        USER_ID("userId"),
        DATE_OF_CREATION("dateOfCreation");

        @Getter
        private final String fieldName;
    }
}