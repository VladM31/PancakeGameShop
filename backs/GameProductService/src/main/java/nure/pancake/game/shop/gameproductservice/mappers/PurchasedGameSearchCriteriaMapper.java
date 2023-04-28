package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.PurchasedGameFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.PurchasedGameSearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchasedGameSearchCriteriaMapper {
    private final ModelMapper mapper= new ModelMapper();

    public PurchasedGameSearchCriteria toPurchasedGameSearchCriteria(PurchasedGameFilter filter){
        return mapper.map(filter,PurchasedGameSearchCriteria.class);
    }
}
