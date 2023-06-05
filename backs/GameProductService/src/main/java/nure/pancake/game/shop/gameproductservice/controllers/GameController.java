package nure.pancake.game.shop.gameproductservice.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.User;
import nure.pancake.game.shop.gameproductservice.dto.DownloadGameRequest;
import nure.pancake.game.shop.gameproductservice.dto.GameLevelsList;
import nure.pancake.game.shop.gameproductservice.dto.GameList;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import nure.pancake.game.shop.gameproductservice.filters.GameLevelsFilter;
import nure.pancake.game.shop.gameproductservice.mappers.DownloadGameMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameLevelsListMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameListMapper;
import nure.pancake.game.shop.gameproductservice.services.GameLevelsService;
import nure.pancake.game.shop.gameproductservice.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final GameLevelsService gameLevelsService;
    private final GameListMapper gameListMapper;
    private final GameLevelsListMapper gameLevelsListMapper;
    private final DownloadGameMapper downloadGameMapper;

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

    @GetMapping("/{gameId}/file/{platform}")
    public ResponseEntity<byte[]> getGameFile(@AuthenticationPrincipal User user, @Valid @ModelAttribute DownloadGameRequest request){
        return gameService.getGameFile(downloadGameMapper.toDownloadGame(request, user.getId()));
    }
}
