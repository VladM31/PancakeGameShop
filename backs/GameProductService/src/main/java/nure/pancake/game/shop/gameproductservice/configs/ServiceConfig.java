package nure.pancake.game.shop.gameproductservice.configs;

import nure.pancake.game.shop.gameproductservice.clients.AuthClient;
import nure.pancake.game.shop.gameproductservice.clients.BuyClient;
import nure.pancake.game.shop.gameproductservice.convector.GameSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.convector.GenreSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.convector.LevelSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.convector.PurchasedGameSortFieldConvector;
import nure.pancake.game.shop.gameproductservice.mappers.*;
import nure.pancake.game.shop.gameproductservice.repositories.*;
import nure.pancake.game.shop.gameproductservice.services.*;
import org.springframework.beans.factory.annotation.Value;
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
            GameMapper gameMapper,
            PurchasedGameRepository purchasedGameRepository
    ) {
        return new GameServiceImpl(gameRepository, gameCriteriaMapper,
                sortFiledConvector, gameMapper,purchasedGameRepository);
    }

    @Bean
    LevelService levelServiceImpl(
            LevelRepository levelRepository,
            LevelSearchCriteriaMapper searchCriteriaMapper,
            LevelSortFiledConvector sortFiledConvector,
            LevelMapper levelMapper
    ) {
        return new LevelServiceImpl(levelRepository, searchCriteriaMapper, sortFiledConvector, levelMapper);
    }

    @Bean
    BuyService BuyServiceImpl(
            PurchasedGameRepository purchasedGameRepository,
            PurchasedLevelRepository purchasedLevelRepository,
            GameRepository gameRepository,
            LevelRepository levelRepository,
            BuyClient buyClient
    ) {
        return new BuyServiceImpl(purchasedGameRepository, purchasedLevelRepository, gameRepository, levelRepository, buyClient);
    }

    @Bean
    PurchasedGamesService purchasedGamesServiceImpl(
            PurchasedGameSearchCriteriaMapper purchasedGameSearchCriteriaMapper,
            PurchasedGameSortFieldConvector sortFieldConvector,
            PurchasedGameMapper purchasedGameMapper,
            PurchasedGameRepository purchasedGameRepository,
            PurchasedGameDetailsMapper gameDetailsMapper,
            GameRepository gameRepository,
            LevelRepository levelRepository
    ) {
        return new PurchasedGamesServiceImpl(
                purchasedGameSearchCriteriaMapper,
                sortFieldConvector,
                purchasedGameMapper,
                purchasedGameRepository,
                gameDetailsMapper,
                gameRepository,
                levelRepository);
    }

    @Bean
    GenreService genreServiceImpl(GenreRepository genreRepository, GenreSortFiledConvector convector){
        return new GenreServiceImpl(genreRepository,convector);
    }

    @Bean
    PromoCodeService promoCodeServiceImpl(
            PromoCodeMapper mapper,
            PromoCodeRepository repository,
            @Value("${promo-code-key}") String key,
            PromoCodeFilterMapper filterMapper){
        return new PromoCodeServiceImpl(mapper,repository,key,filterMapper);
    }
}
