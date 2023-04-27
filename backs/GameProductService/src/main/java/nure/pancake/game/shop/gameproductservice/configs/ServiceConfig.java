package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.clients.AuthClient;
import nure.pancake.game.shop.gameproductservice.convector.GameSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.mappers.*;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import nure.pancake.game.shop.gameproductservice.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    AuthService authServiceImpl(AuthClient client) {
        return new AuthServiceImpl(client);
    }

    @Bean
    GameLevelsService gameLevelsServiceImpl(
            EntityGameLevelMapper entityGameLevelMapper,
            GameRepository gameRepository,
            LevelRepository levelRepository,
            GameLevelsSearchCriteriaMapper searchCriteriaMapper,
            GameSortFiledConvector sortFiledConvector,
            GameLevelsMapper gameLevelsMapper
    ) {
        return new GameLevelsServiceImpl(entityGameLevelMapper, gameRepository, levelRepository, searchCriteriaMapper,
                sortFiledConvector, gameLevelsMapper);
    }

    @Bean
    GameService gameServiceImpl(
            GameRepository gameRepository,
            GameSearchCriteriaMapper gameCriteriaMapper,
            GameSortFiledConvector sortFiledConvector,
            GameMapper gameMapper
    ) {
        return new GameServiceImpl(gameRepository, gameCriteriaMapper, sortFiledConvector, gameMapper);
    }
}
