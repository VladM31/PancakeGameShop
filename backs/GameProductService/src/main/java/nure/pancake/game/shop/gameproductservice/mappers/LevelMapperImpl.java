package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import org.springframework.stereotype.Component;

@Component
public class LevelMapperImpl implements LevelMapper {
    @Override
    public Level toLevel(LevelEntity entity) {
        return Level.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .mainImage(entity.getMainImage())
                .images(entity.getImages())
                .gameId(entity.getGameId())
                .build();
    }
}
