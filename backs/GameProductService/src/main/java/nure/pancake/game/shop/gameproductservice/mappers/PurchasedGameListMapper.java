package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGame;
import nure.pancake.game.shop.gameproductservice.dto.PurchasedGameList;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PurchasedGameListMapper {
    private final ModelMapper mapper = new ModelMapper();

    public PurchasedGameList toPurchasedGameList(Page<PurchasedGame> pages){
        return new PurchasedGameList(
                pages.map(p -> mapper.map(p,PurchasedGameList.PurchasedGameDto.class))
        );
    }
}
