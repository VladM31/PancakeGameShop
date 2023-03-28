package nure.pancake.game.shop.gameproductservice.repositories;

import nure.pancake.game.shop.gameproductservice.entity.Level;
import org.springframework.data.repository.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface LevelRepository extends Repository<Level, Long>, JpaSpecificationExecutor<Level> {
    Level save(@NonNull Level level);

    default Level update(@NonNull Level level) {
        return save(level);
    }
}