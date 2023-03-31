package nure.pancake.game.shop.authorizationservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15")
    @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15")
    @NotBlank(message = "Phone number mustn't be blank")
    @NotNull(message = "Phone number mustn't be null")
    private String phoneNumber;
    @Size(min = 8, max = 60, message = "Password length must be between 8 and 60")
    @NotBlank(message = "Password mustn't be blank")
    @NotNull(message = "Password mustn't be null")
    private String password;
}
