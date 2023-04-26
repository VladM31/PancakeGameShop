package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.GameSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.GameSortFiled;
import nure.pancake.game.shop.gameproductservice.filters.GameFilter;
import nure.pancake.game.shop.gameproductservice.mappers.GameMapper;
import nure.pancake.game.shop.gameproductservice.mappers.GameSearchCriteriaMapper;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameSearchCriteriaMapper gameCriteriaMapper;
    private final GameSortFiledConvector sortFiledConvector;
    private final GameMapper gameMapper;

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
}
