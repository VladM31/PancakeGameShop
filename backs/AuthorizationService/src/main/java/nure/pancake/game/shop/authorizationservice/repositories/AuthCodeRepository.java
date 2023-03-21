package nure.pancake.game.shop.authorizationservice.repositories;

import jakarta.transaction.Transactional;
import nure.pancake.game.shop.authorizationservice.entities.AuthCodeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface AuthCodeRepository extends
        Repository<AuthCodeEntity, Long>,
        JpaSpecificationExecutor<AuthCodeEntity> {

    AuthCodeEntity save(AuthCodeEntity e);

    default AuthCodeEntity update(AuthCodeEntity e) {
        return save(e);
    }


    @Modifying
    @Transactional
    @Query(value = "UPDATE AuthCodeEntity SET " +
            "active = CASE WHEN :#{#e.active} IS NULL THEN active ELSE :#{#e.active} END," +
            "dateOfCreation = CASE WHEN :#{#e.dateOfCreation} IS NULL THEN dateOfCreation ELSE :#{#e.dateOfCreation} END, " +
            "value = CASE WHEN :#{#e.value} IS NULL THEN value ELSE :#{#e.value} END, " +
            "userId = CASE WHEN :#{#e.userId} IS NULL THEN userId ELSE :#{#e.userId} END  " +
            "WHERE id = :#{#e.id}")
    int merge(@Param("e") AuthCodeEntity e);
}
