package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface GenreRepository extends Repository<GenreEntity, Integer>, JpaSpecificationExecutor<GenreEntity> {
    GenreEntity save(@NonNull GenreEntity genreEntity);

    default GenreEntity update(@NonNull GenreEntity genreEntity) {
        return save(genreEntity);
    }
}