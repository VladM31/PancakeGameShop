package telegram.bot.nure.botphonenumberprodject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
public class BotPhoneNumberProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotPhoneNumberProjectApplication.class, args);
    }

}
