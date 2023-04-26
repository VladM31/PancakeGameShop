package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.filters.GenreFilter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

public interface GenreService {
    Page<String> findBy(GenreFilter filter);

    String update(@NonNull String oldGenre, String newGenre);

    String save(String genre);
}
