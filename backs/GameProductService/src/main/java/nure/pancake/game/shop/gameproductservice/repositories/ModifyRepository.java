package nure.pancake.game.shop.gameproductservice.repositories;

import org.springframework.lang.NonNull;

public interface ModifyRepository<Entity> {
    Entity save(@NonNull Entity entity);

    default Entity update(@NonNull Entity entity) {
        return save(entity);
    }
}
