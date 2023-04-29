package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameDetailsList;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PurchasedGameDetailsListMapper {
    private final ModelMapper mapper = new ModelMapper();

    public PurchasedGameDetailsList toPurchasedGameDetailsList(Page<PurchasedGameDetails> pages){
        return new PurchasedGameDetailsList(
                pages.map(p -> mapper.map(p,PurchasedGameDetailsList.PurchasedGameDetailsDto.class))
        );
    }
}
