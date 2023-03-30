package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpUser {
    private String phoneNumber;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String currency;
    private String email;

}
