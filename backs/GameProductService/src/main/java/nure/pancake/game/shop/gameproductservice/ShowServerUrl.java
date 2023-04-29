package nure.pancake.game.shop.gameproductservice;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowServerUrl implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ShowServerUrl.class);
    private final Environment env;

    @Override
    public void run(String... args) throws Exception {
        String serverUrl = "http://localhost:" + env.getProperty("server.port") + env.getProperty("server.servlet.context-path","");
        LOG.info("\n\nServer url: " + serverUrl + "\n");
    }
}
