package nure.pancake.game.shop.authorizationservice.repositories;

import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.utils.Utils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;

import java.util.Optional;


public interface UserRepository extends Repository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    @CachePut(value = "userEntities", key = "#user.id", condition = "#user.id != null",
            cacheManager = "constantLifetimeCacheManager")
    UserEntity save(@NonNull UserEntity user);

    @CachePut(value = "userEntities", key = "#user.id", condition = "#user.id != null",
            cacheManager = "constantLifetimeCacheManager")
    default UserEntity update(@NonNull UserEntity user) {
        return save(user);
    }

    @Cacheable(value = "userEntities", key = "#id", condition = "#id != null",
            cacheManager = "constantLifetimeCacheManager")
    Optional<UserEntity> findById(@NonNull Long id);

    @CachePut(value = "userEntities", key = "#oldUser.id", condition = "#oldUser.id != null",
            cacheManager = "constantLifetimeCacheManager")
    default UserEntity merge(@NonNull UserEntity newUser, @NonNull UserEntity oldUser) {
        UserEntity user = new UserEntity();

        user.setId(Utils.ifNullElse(UserEntity::getId, newUser, oldUser));
        user.setPhoneNumber(Utils.ifNullElse(UserEntity::getPhoneNumber, newUser, oldUser));
        user.setPassword(Utils.ifNullElse(UserEntity::getPassword, newUser, oldUser));
        user.setNickname(Utils.ifNullElse(UserEntity::getNickname, newUser, oldUser));
        user.setFirstName(Utils.ifNullElse(UserEntity::getFirstName, newUser, oldUser));
        user.setLastName(Utils.ifNullElse(UserEntity::getLastName, newUser, oldUser));
        user.setBirthDate(Utils.ifNullElse(UserEntity::getBirthDate, newUser, oldUser));
        user.setActive(Utils.ifNullElse(UserEntity::getActive, newUser, oldUser));
        user.setSelectedCurrency(Utils.ifNullElse(UserEntity::getSelectedCurrency, newUser, oldUser));
        user.setUserState(Utils.ifNullElse(UserEntity::getUserState, newUser, oldUser));
        user.setEmail(Utils.ifNullElse(UserEntity::getEmail, newUser, oldUser));
        user.setDateRegistration(Utils.ifNullElse(UserEntity::getDateRegistration, newUser, oldUser));
        user.setRole(Utils.ifNullElse(UserEntity::getRole, newUser, oldUser));

        return save(user);
    }
}
