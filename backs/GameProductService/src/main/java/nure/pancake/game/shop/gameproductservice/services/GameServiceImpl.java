package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.GameSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.DownloadGame;
import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GameSortFiled;
import nure.pancake.game.shop.gameproductservice.exceptions.BuyContentException;
import nure.pancake.game.shop.gameproductservice.exceptions.GameFileNotFound;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import nure.pancake.game.shop.gameproductservice.mappers.GameMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameSearchCriteriaMapper;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.PurchasedGameRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.PurchasedGameSearchCriteria;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameSearchCriteriaMapper gameCriteriaMapper;
    private final GameSortFiledConvector sortFiledConvector;
    private final GameMapper gameMapper;
    private final PurchasedGameRepository purchasedGameRepository;

    @Override
    public Page<Game> findBy(GameFilter filter) {
        return gameRepository.findAll(
                gameCriteriaMapper.toGameSearchCriteria(filter),
                filter.toPageable(0, 100, GameSortFiled.NAME, sortFiledConvector::convert)

        ).map(gameMapper::toGame);
    }

    @Override
    public boolean update(Game game) {
        return gameRepository.update(gameMapper.toGameEntity(game)) != null;
    }

    @Override
    public boolean save(Game game) {
        return gameRepository.save(gameMapper.toGameEntity(game)) != null;
    }

    @Override
    public ResponseEntity<byte[]> getGameFile(DownloadGame dg) {
        var gameOpt =gameRepository.findOne(GameSearchCriteria
                .builder()
                .gameId(dg.gameId())
                .platform(dg.platform().name())
                .build());

        if(gameOpt.isEmpty()){
            throw new GameFileNotFound("Гра не існує");
        }

        if(!purchasedGameRepository.exists(
                PurchasedGameSearchCriteria
                        .builder()
                        .gameId(dg.gameId())
                        .userId(dg.userId())
                        .build())){
            throw new BuyContentException("Content not bought");
        }

        ClassPathResource resource = new ClassPathResource(toPath(dg));

        if(!resource.exists()){
            throw new GameFileNotFound("Файл гри не існує.");
        }

        try {
            byte[] data = resource.getInputStream().readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentLength(data.length);
            headers.set("Content-Disposition", "attachment; filename=" + gameOpt.get().getName() + ".zip");

            return new ResponseEntity<>(data, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new GameFileNotFound("Файл гри не існує. " + e.getMessage());
        }
    }

    private String toPath(DownloadGame dg){
        return "files/games/game_" + dg.gameId() + "_" + dg.platform() + ".zip";
    }
}
