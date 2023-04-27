package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.mappers.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:props/image.path.yml")
public class MapperConfig {
    @Bean
    ServiceUrlFactory serviceUrlFactoryImpl(
            @Value("${server.host}") String host,
            @Value("${server.port}") String port,
            @Value("${server.servlet.context-path}") String contextPath
    ){
        return new ServiceUrlFactoryImpl(host, port, contextPath);
    }

    @Bean
    GameUrlMapper gameUrlMapper(
            @Value("${game-icon-path}") String iconUrl,
            @Value("${game-main-image-path}") String mainImageUrl,
            @Value("${game-screenshots-path}") String imagesUrl,
            ServiceUrlFactory urlFactory
    ){
        return new GameUrlMapper(
                urlFactory.makeUrl(iconUrl),
                urlFactory.makeUrl(mainImageUrl),
                urlFactory.makeUrl(imagesUrl)
        );
    }

    @Bean
    GameListMapper gameListMapper(GameUrlMapper gameUrlMapper){
        return new GameListMapper(gameUrlMapper);
    }

    @Bean
    LevelRespondMapper levelRespondMapper(
            @Value("${level-main-image-path}") String mainImageUrl,
            @Value("${level-screenshots-path}") String imagesUrl,
            ServiceUrlFactory urlFactory
    ){
        return new LevelRespondMapper(
                urlFactory.makeUrl(mainImageUrl),
                urlFactory.makeUrl(imagesUrl)
        );
    }
}
