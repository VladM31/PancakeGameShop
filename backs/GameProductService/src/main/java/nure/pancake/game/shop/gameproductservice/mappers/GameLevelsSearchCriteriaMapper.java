package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.GameLevelsFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class GameLevelsSearchCriteriaMapper {
    private final ModelMapper mapper;

    public GameLevelsSearchCriteriaMapper() {
        this.mapper = new ModelMapper();
        mapFrom();
    }

    private void mapFrom() {
        TypeMap<GameLevelsFilter, GameSearchCriteria> propertyMapperFrom =
                this.mapper.createTypeMap(GameLevelsFilter.class, GameSearchCriteria.class);

        propertyMapperFrom.addMappings(
                vc -> vc.map(GameLevelsFilter::getGenres,GameSearchCriteria::setGenreNames)
        );

    }

    public GameSearchCriteria toGameSearchCriteria(GameLevelsFilter filter){
        return mapper.map(filter, GameSearchCriteria.class);
    }

    public LevelSearchCriteria toLevelSearchCriteria(GameLevelsFilter f){
        return LevelSearchCriteria.builder()
                .levelIds(f.getLevelIds())
                .gameIds(f.getGameIds())
                .build();
    }
}
