package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGame;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PurchasedGameMapper {
    private final ModelMapper mapper = new ModelMapper();

    public PurchasedGame toPurchasedGame(PurchasedGameEntity entity){
        return mapper.map(entity,PurchasedGame.class);
    }

}
