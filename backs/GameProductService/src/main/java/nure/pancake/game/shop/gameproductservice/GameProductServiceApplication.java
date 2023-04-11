package nure.pancake.game.shop.gameproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(
        exclude = UserDetailsServiceAutoConfiguration.class
)
public class GameProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameProductServiceApplication.class, args);
    }

}
