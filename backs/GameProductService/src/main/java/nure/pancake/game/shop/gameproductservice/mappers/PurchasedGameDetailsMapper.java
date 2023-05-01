package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.PurchasedGameDetails;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import org.modelmapper.ModelMapper;

import java.util.Map;

@RequiredArgsConstructor
public class PurchasedGameDetailsMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final String gameMainImageUrl;
    private final String levelMainImageUrl;

    public PurchasedGameDetails toPurchasedGameDetails(
            PurchasedGameEntity entity,
            GameEntity gameEntity,
            Map<Long, LevelEntity> levelById) {
        PurchasedGameDetails gameDetails = mapper.map(entity, PurchasedGameDetails.class);
        gameDetails.setMainImage(gameMainImageUrl + gameEntity.getMainImage());
        gameDetails.setName(gameEntity.getName());

        gameDetails.getLevels().forEach(l -> {
            l.setMainImage(levelMainImageUrl + levelById.get(l.getLevelsId()).getMainImage());
            l.setName(levelById.get(l.getLevelsId()).getName());
        });

        return gameDetails;
    }

}
