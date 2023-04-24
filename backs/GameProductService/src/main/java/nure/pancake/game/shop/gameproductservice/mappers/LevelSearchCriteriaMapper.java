package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.LevelFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class LevelSearchCriteriaMapper {
    private final ModelMapper mapper;

    public LevelSearchCriteriaMapper() {
        this.mapper = new ModelMapper();
        mapFrom();
    }

    private void mapFrom() {
        TypeMap<LevelFilter, LevelSearchCriteria> propertyMapperFrom =
                this.mapper.createTypeMap(LevelFilter.class, LevelSearchCriteria.class);

        propertyMapperFrom.addMappings(
                vc -> vc.map(LevelFilter::getGameIds, LevelSearchCriteria::setGameIds)
        );

    }

    public LevelSearchCriteria toLevelSearchCriteria(LevelFilter l) {
        return LevelSearchCriteria.builder()
                .levelIds(l.getId())
                .gameIds(l.getGameIds())
                .name(l.getName())
                .price(l.getPrice())
                .hidden(l.getHidden())
                .build();
    }
}
