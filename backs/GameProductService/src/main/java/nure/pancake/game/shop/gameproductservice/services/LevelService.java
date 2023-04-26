package nure.pancake.game.shop.gameproductservice.services;

import nure.pancake.game.shop.gameproductservice.dataobjects.Level;
import nure.pancake.game.shop.gameproductservice.filters.LevelFilter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

public interface LevelService {
    Page<Level> findBy(LevelFilter filter);

    boolean update(@NonNull Level level);

    boolean save(Level levels);
}
