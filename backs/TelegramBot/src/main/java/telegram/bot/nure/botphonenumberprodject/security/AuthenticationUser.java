package telegram.bot.nure.botphonenumberprodject.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationUser {
    private Service service;
    private String name;
}
