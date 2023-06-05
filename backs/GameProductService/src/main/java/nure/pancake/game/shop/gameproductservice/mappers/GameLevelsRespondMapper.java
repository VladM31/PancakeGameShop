package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.EqualsAndHashCode;
import nure.pancake.game.shop.gameproductservice.dataobjects.GameLevels;
import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.dto.GameLevelsRespond;
import nure.pancake.game.shop.gameproductservice.dto.LevelRespond;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EqualsAndHashCode
public class GameLevelsRespondMapper {
    private final ModelMapper mapper;

    public GameLevelsRespondMapper(LevelRespondMapper levelRespondMapper, GameUrlMapper gameUrlMapper ) {
        this.mapper = new ModelMapper();
        prepareMap( levelRespondMapper, gameUrlMapper);
    }

    private void prepareMap(LevelRespondMapper levelRespondMapper, GameUrlMapper gameUrlMapper ) {
        TypeMap<GameLevels, GameLevelsRespond> propertyMapperFrom =
                this.mapper.createTypeMap(GameLevels.class, GameLevelsRespond.class);

        Converter<List<Level>, List<LevelRespond>> toLevelRespond = c ->c.getSource().stream().map(levelRespondMapper::toLevelRespond).toList();

        propertyMapperFrom.addMappings(ep -> {
            ep.using(gameUrlMapper.toIconUrl()).map(GameLevels::getIcon,GameLevelsRespond::setIcon);
            ep.using(gameUrlMapper.toMainImageUrl()).map(GameLevels::getMainImage,GameLevelsRespond::setMainImage);
            ep.using(gameUrlMapper.toImagesUrl()).map(GameLevels::getImages,GameLevelsRespond::setImages);
            ep.map(GameLevels::getVideoLink, GameLevelsRespond::setVideoUrl);
            ep.using(toLevelRespond).map(GameLevels::getLevels,GameLevelsRespond::setLevels);
        });
    }

    public GameLevelsRespond toGameLevelsRespond(GameLevels gameLevels){
        return mapper.map(gameLevels,GameLevelsRespond.class);
    }
}
