package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.GameSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GameSortFiled;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.filters.GameLevelsFilter;
import nure.pancake.game.shop.gameproductservice.mappers.CameLevelsSearchCriteriaMapper;
import nure.pancake.game.shop.gameproductservice.mappers.EntityGameLevelMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameLevelsMapper;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GameLevelsServiceImpl implements GameLevelsService {
    private final EntityGameLevelMapper entityGameLevelMapper;
    private final GameRepository gameRepository;
    private final LevelRepository levelRepository;
    private final CameLevelsSearchCriteriaMapper searchCriteriaMapper;
    private final GameSortFiledConvector sortFiledConvector;
    private final GameLevelsMapper gameLevelsMapper;

    @Override
    public Page<GameLevels> findBy(GameLevelsFilter f) {
        Map<Long, List<LevelEntity>> levelEntityListMap = levelRepository.findAll(searchCriteriaMapper.toLevelSearchCriteria(f))
                .stream().collect(Collectors.groupingBy(LevelEntity::getGameId));

        var games = gameRepository.findAll(searchCriteriaMapper.toGameSearchCriteria(f),
                f.toPageable(0, 100, GameSortFiled.NAME, sortFiledConvector::convert));

        return games.map(g ->
                gameLevelsMapper.toGameLevels(g, levelEntityListMap.getOrDefault(g.getId(), List.of()))
        );
    }

    @Override
    public boolean save(GameLevels gameLevels) {
        var game = entityGameLevelMapper.toGameEntity(gameLevels);

        var gameId = Objects.requireNonNull(gameRepository.save(game).getId(), "Game id is null");

        entityGameLevelMapper
                .toLevelEntityList(gameLevels)
                .stream()
                .peek(l -> l.setGameId(gameId))
                .forEach(levelRepository::save);

        return true;
    }
}
