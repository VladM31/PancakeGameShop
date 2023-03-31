package nure.pancake.game.shop.authorizationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmCodeRequest {
    @Pattern(regexp = "[a-zA-Z0-9]{30}", message = "Code is not correct")
    @NotBlank(message = "Code mustn't be blank")
    @NotNull(message = "Code mustn't be null")
    private String code;
    @Pattern(regexp = "\\d{10,15}", message = "Phone number length must be between 10 and 15")
    @Size(min = 10, max = 15, message = "Phone number length must be between 10 and 15")
    @NotBlank(message = "Phone number mustn't be blank")
    @NotNull(message = "Phone number mustn't be null")
    private String phoneNumber;
}
