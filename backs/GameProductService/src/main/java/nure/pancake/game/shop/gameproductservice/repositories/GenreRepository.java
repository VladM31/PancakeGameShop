package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entity.Genre;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface GenreRepository extends Repository<Genre, Integer>, JpaSpecificationExecutor<Genre> {
    Genre save(@NonNull Genre genre);

    default Genre update(@NonNull Genre genre) {
        return save(genre);
    }
}