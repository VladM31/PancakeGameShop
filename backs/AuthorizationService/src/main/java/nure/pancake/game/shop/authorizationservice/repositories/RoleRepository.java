package nure.pancake.game.shop.authorizationservice.repositories;

import nure.pancake.game.shop.authorizationservice.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

public interface RoleRepository extends
        Repository<RoleEntity, Long>,
        JpaSpecificationExecutor<RoleEntity> {
}
