package nure.pancake.game.shop.gameproductservice.mappers;

import org.modelmapper.Converter;

import java.util.Collection;

public class GameUrlMapper {
    private final String iconUrl;
    private final String mainImageUrl;
    private final String imagesUrl;

    public GameUrlMapper(String iconUrl, String mainImageUrl, String imagesUrl) {
        this.iconUrl = iconUrl;
        this.mainImageUrl = mainImageUrl;
        this.imagesUrl = imagesUrl;
    }

    public Converter<String, String> toIconUrl(){
        return c -> this.iconUrl + c.getSource();
    }
    public Converter<String, String> toMainImageUrl(){
        return c -> this.mainImageUrl + c.getSource();
    }
    public Converter<Collection<String>, Collection<String>> toImagesUrl(){
        return c -> c.getSource().stream().map(s -> this.imagesUrl + s).toList();
    }
}
