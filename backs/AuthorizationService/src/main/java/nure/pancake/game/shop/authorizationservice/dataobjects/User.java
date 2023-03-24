package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String phoneNumber;
    private String nickname;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Boolean active;
    private String selectedCurrency;
    private UserState userState;
    private String email;
    private LocalDateTime dateRegistration;
    private Role role;
}
