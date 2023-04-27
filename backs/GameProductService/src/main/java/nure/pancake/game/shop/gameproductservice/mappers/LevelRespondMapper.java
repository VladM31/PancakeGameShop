package nure.pancake.game.shop.gameproductservice.mappers;

import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.dto.LevelRespond;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Collection;

public class LevelRespondMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final String mainImageUrl;
    private final String imagesUrl;

    public LevelRespondMapper(String mainImageUrl, String imagesUrl) {
        this.mainImageUrl = mainImageUrl;
        this.imagesUrl = imagesUrl;
        prepareMap();
    }

    private void prepareMap() {
        TypeMap<Level, LevelRespond> propertyMapperFrom =
                this.mapper.createTypeMap(Level.class, LevelRespond.class);

        Converter<String, String> toMainImageUrl = c -> this.mainImageUrl + c.getSource();
        Converter<Collection<String>, Collection<String>> toImagesUrl = c -> c.getSource().stream().map(s -> this.imagesUrl + s).toList();

        propertyMapperFrom.addMappings(ep -> {
            ep.using(toMainImageUrl).map(Level::getMainImage,LevelRespond::setMainImage);
            ep.using(toImagesUrl).map(Level::getImages,LevelRespond::setImages);
        });
    }

    public LevelRespond toLevelRespond(Level level){
        return mapper.map(level,LevelRespond.class);
    }
}
