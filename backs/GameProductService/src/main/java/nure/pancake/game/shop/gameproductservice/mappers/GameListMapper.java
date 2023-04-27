package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Game;
import nure.pancake.game.shop.gameproductservice.dto.GameList;
import nure.pancake.game.shop.gameproductservice.dto.GameRespond;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;

public class GameListMapper {
    private final ModelMapper mapper;


    public GameListMapper(GameUrlMapper gameUrlMapper) {
        this.mapper = new ModelMapper();
        prepareMap(gameUrlMapper);
    }

    private void prepareMap(GameUrlMapper gameUrlMapper) {
        TypeMap<Game, GameRespond> propertyMapperFrom =
                this.mapper.createTypeMap(Game.class, GameRespond.class);


        propertyMapperFrom.addMappings(ep -> {
            ep.using(gameUrlMapper.toIconUrl()).map(Game::getIcon,GameRespond::setIcon);
            ep.using(gameUrlMapper.toMainImageUrl()).map(Game::getMainImage,GameRespond::setMainImage);
            ep.using(gameUrlMapper.toImagesUrl()).map(Game::getImages,GameRespond::setImages);
        });
    }

    public GameList toGameList(Page<Game> games){
        return new GameList(
                games.map(g ->this.mapper.map(g,GameRespond.class))
        );
    }
}
