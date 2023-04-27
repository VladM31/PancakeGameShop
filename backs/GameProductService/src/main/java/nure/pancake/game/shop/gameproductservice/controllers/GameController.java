package nure.pancake.game.shop.gameproductservice.controllers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dto.GameLevelsList;
import nure.pancake.game.shop.gameproductservice.dto.GameList;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import nure.pancake.game.shop.gameproductservice.filters.GameLevelsFilter;
import nure.pancake.game.shop.gameproductservice.mappers.GameLevelsListMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameListMapper;
import nure.pancake.game.shop.gameproductservice.services.GameLevelsService;
import nure.pancake.game.shop.gameproductservice.services.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final GameLevelsService gameLevelsService;
    private final GameListMapper gameListMapper;
    private final GameLevelsListMapper gameLevelsListMapper;

    @GetMapping
    public GameList getGames(GameFilter filter){
        return gameListMapper.toGameList(
                gameService.findBy(filter)
        );
    }

    @GetMapping("/levels")
    public GameLevelsList getGameWithLevels(GameLevelsFilter filter){
        return gameLevelsListMapper.toGameLevelsList(
                gameLevelsService.findBy(filter)
        );
    }
}
