package nure.pancake.game.shop.authorizationservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nure.pancake.game.shop.authorizationservice.validation.CurrencyName;
import nure.pancake.game.shop.authorizationservice.validation.NullableEmail;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15")
    @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15")
    @NotBlank(message = "Phone number mustn't be blank")
    @NotNull(message = "Phone number mustn't be null")
    private String phoneNumber;
    @Size(min = 8, max = 60, message = "Password length must be between 8 and 60")
    @NotBlank(message = "Password mustn't be blank")
    @NotNull(message = "Password mustn't be null")
    private String password;
    @Size(min = 3, max = 255, message = "Nickname length must be between 3 and 255")
    @NotBlank(message = "Nickname mustn't be blank")
    @NotNull(message = "Nickname mustn't be null")
    private String nickname;
    @Size(min = 2, max = 60, message = "First name length must be between 2 and 60")
    @NotBlank(message = "First name number mustn't be blank")
    @NotNull(message = "First name mustn't be null")
    private String firstName;
    @Size(min = 2, max = 60, message = "Last name length must be between 2 and 60")
    @NotBlank(message = "Last name number mustn't be blank")
    @NotNull(message = "Last name mustn't be null")
    private String lastName;
    @NotNull(message = "Birth date mustn't be null")
    @Past(message = "Birth date must be past")
    private LocalDate birthDate;
    @NotNull(message = "Currency date mustn't be null")
    @CurrencyName(message = "Currency length must be 3 and name must matches [a-zA-Z]+")
    private String currency;
    @NullableEmail(min = 5, max = 60)
    private String email;
}
