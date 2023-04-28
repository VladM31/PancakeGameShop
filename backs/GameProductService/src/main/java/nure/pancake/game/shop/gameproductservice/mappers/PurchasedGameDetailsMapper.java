package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PurchasedGameDetailsMapper {
    private final ModelMapper mapper = new ModelMapper();

    public PurchasedGameDetails toPurchasedGameDetails(
            PurchasedGameEntity entity,
            GameEntity gameEntity,
            Map<Long, LevelEntity> levelById){
        PurchasedGameDetails gameDetails = mapper.map(entity,PurchasedGameDetails.class);
        gameDetails.setMainImage(gameEntity.getMainImage());
        gameDetails.setName(gameEntity.getName());

        gameDetails.getLevels().forEach(l -> {
            l.setMainImage(levelById.get(l.getLevelsId()).getMainImage());
            l.setName(levelById.get(l.getLevelsId()).getName());
        });

        return gameDetails;
    }

}
