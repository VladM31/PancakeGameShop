package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.PurchasedGameSortFieldConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGame;
import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.PurchasedGameSortField;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;
import nure.pancake.game.shop.gameproductservice.filters.PurchasedGameFilter;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameDetailsMapper;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameMapper;
import nure.pancake.game.shop.gameproductservice.mappers.PurchasedGameSearchCriteriaMapper;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import nure.pancake.game.shop.gameproductservice.repositories.PurchasedGameRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PurchasedGamesServiceImpl implements PurchasedGamesService {
    private final PurchasedGameSearchCriteriaMapper purchasedGameSearchCriteriaMapper;
    private final PurchasedGameSortFieldConvector sortFieldConvector;
    private final PurchasedGameMapper purchasedGameMapper;
    private final PurchasedGameRepository purchasedGameRepository;
    private final PurchasedGameDetailsMapper gameDetailsMapper;
    private final GameRepository gameRepository;
    private final LevelRepository levelRepository;


    public Page<PurchasedGame> findBy(PurchasedGameFilter filter) {
        return purchasedGameRepository.findAll(
                purchasedGameSearchCriteriaMapper.toPurchasedGameSearchCriteria(filter),
                filter.toPageable(0, 100, PurchasedGameSortField.BUY_DATE, sortFieldConvector::convert)
        ).map(purchasedGameMapper::toPurchasedGame);
    }

    @Override
    public Page<PurchasedGameDetails> findDetailsBy(PurchasedGameFilter filter) {
        var list = purchasedGameRepository.findAll(
                purchasedGameSearchCriteriaMapper.toPurchasedGameSearchCriteria(filter),
                filter.toPageable(0, 100, PurchasedGameSortField.BUY_DATE, sortFieldConvector::convert)
        );

        var mapId = list.stream().collect(Collectors.toMap(PurchasedGameEntity::getGamesId,
                p -> p.getLevels().stream().map(PurchasedLevelEntity::getLevelsId)));

        var gameById = gameRepository.findAll(GameSearchCriteria.builder().gameIds(mapId.keySet()).build())
                .stream().collect(Collectors.toMap(GameEntity::getId, g -> g));
        var levelByGameId = levelRepository.findAll(LevelSearchCriteria.builder().levelIds(
                mapId.values().stream().flatMap(levelIds -> levelIds).toList()
        ).build())
                .stream()
                .collect(
                        Collectors.groupingBy(LevelEntity::getGameId,Collectors.toMap(LevelEntity::getId, l -> l)));

        return list.map(p -> gameDetailsMapper
                .toPurchasedGameDetails(
                p, gameById.get(p.getGamesId()),
                levelByGameId.get(p.getGamesId())
        ));
    }

}
