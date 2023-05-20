package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.filters.PromoCodeFilter;
import nure.pancake.game.shop.gameproductservice.search.criteria.PromoCodeSearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PromoCodeFilterMapper {
    private final ModelMapper mapper= new ModelMapper();

    public PromoCodeSearchCriteria toPromoCodeSearchCriteria(PromoCodeFilter filter){
        return mapper.map(filter,PromoCodeSearchCriteria.class);
    }
}
