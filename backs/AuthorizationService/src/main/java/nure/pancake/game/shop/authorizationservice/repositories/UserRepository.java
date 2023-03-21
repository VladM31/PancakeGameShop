package nure.pancake.game.shop.authorizationservice.repositories;

import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;


public interface UserRepository extends Repository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity save(UserEntity entity);
}
