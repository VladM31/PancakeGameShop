package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameMapperImpl implements GameMapper {
    private final ModelMapper mapper = new ModelMapper();
    @Override
    public Game toGame(GameEntity entity) {
        return Game.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .ageRating(entity.getAgeRating())
                .releaseDate(entity.getReleaseDate())
                .icon(entity.getIcon())
                .mainImage(entity.getMainImage())
                .images(entity.getImages())
                .genres(entity.getGenreEntities().stream().map(GenreEntity::getName).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public GameEntity toGameEntity(Game game) {
        return mapper.map(game, GameEntity.class);
    }
}
