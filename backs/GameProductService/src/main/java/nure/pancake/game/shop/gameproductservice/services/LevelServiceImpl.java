package nure.pancake.game.shop.gameproductservice.services;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.convector.LevelSortFiledConvector;
import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.dataobjects.sortfiled.LevelSortFiled;
import nure.pancake.game.shop.gameproductservice.filters.LevelFilter;
import nure.pancake.game.shop.gameproductservice.mappers.LevelMapper;
import nure.pancake.game.shop.gameproductservice.mappers.LevelSearchCriteriaMapper;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;
    private final LevelSearchCriteriaMapper searchCriteriaMapper;
    private final LevelSortFiledConvector sortFiledConvector;
    private final LevelMapper levelMapper;

    @Override
    public Page<Level> findBy(LevelFilter filter) {
        return levelRepository.findAll(
                searchCriteriaMapper.toLevelSearchCriteria(filter),
                filter.toPageable(0, 100, LevelSortFiled.GAME_ID, sortFiledConvector::convert)

        ).map(levelMapper::toLevel);
    }

    @Override
    public boolean update(Level level) {
        return levelRepository.update(levelMapper.toLevelEntity(level)) != null;
    }

    @Override
    public boolean save(Level level) {
        return levelRepository.save(levelMapper.toLevelEntity(level)) != null;
    }
}
