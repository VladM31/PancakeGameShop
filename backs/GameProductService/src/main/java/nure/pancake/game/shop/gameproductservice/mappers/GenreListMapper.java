package nure.pancake.game.shop.gameproductservice.mappers;

import lombok.RequiredArgsConstructor;
import nure.pancake.game.shop.gameproductservice.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreListMapper {
    private final ModelMapper mapper = new ModelMapper();

    public GenreList toGenreList(Page<String> genres) {
        return new GenreList(
                genres.map(g -> this.mapper.map(g, String.class))
        );
    }
}
