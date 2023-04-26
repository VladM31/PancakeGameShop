package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LevelMapperImpl implements LevelMapper {
    private final ModelMapper mapper = new ModelMapper();
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

    @Override
    public LevelEntity toLevelEntity(Level level) {
        return mapper.map(level, LevelEntity.class);
    }
}
