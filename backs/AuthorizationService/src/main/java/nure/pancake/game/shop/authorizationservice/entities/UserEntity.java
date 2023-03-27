package nure.pancake.game.shop.authorizationservice.entities;

import jakarta.persistence.*;
import lombok.*;
import nure.pancake.game.shop.authorizationservice.dataobjects.UserState;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "selected_currency", nullable = false)
    private String selectedCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private UserState userState;

    @Nullable
    @Column(name = "email")
    private String email;

    @Column(name = "date_registration", nullable = false)
    private LocalDateTime dateRegistration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_table_id", nullable = false)
    private RoleEntity role;

    @RequiredArgsConstructor
    public enum FieldName implements FieldNameGettable {
        ROLE("role"),
        USER_ID("id"),
        EMAIL("email"),
        ACTIVE("active"),
        NICKNAME("nickname"),
        PASSWORD("password"),
        LAST_NAME("lastName"),
        FIRST_NAME("firstName"),
        BIRTH_DATE("birthDate"),
        USER_STATE("userState"),
        PHONE_NUMBER("phoneNumber"),
        SELECTED_CURRENCY("selectedCurrency"),
        DATE_REGISTRATION("dateRegistration");

        @Getter
        private final String fieldName;
    }
}
