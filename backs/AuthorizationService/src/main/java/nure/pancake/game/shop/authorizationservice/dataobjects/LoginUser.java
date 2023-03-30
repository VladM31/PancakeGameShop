package nure.pancake.game.shop.authorizationservice.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private String phoneNumber;
    private String password;
}
