package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.dataobjects.Platforms;
import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameLevelsMapperImpl implements GameLevelsMapper {
    private final LevelMapper mapper;

    public GameLevels toGameLevels(GameEntity game, List<LevelEntity> levels) {
        return GameLevels.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .price(game.getPrice())
                .ageRating(game.getAgeRating())
                .releaseDate(game.getReleaseDate())
                .icon(game.getIcon())
                .mainImage(game.getMainImage())
                .images(game.getImages())
                .videoLink(game.getVideoLink())
                .platforms(game.getPlatforms().stream().map(Platforms::valueOf).toList())
                .genres(game.getGenreEntities().stream().map(GenreEntity::getName).collect(Collectors.toSet()))
                .levels(levels.stream().map(mapper::toLevel).collect(Collectors.toList()))
                .build();
    }
}
