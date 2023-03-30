package nure.pancake.game.shop.authorizationservice.mappers;

import nure.pancake.game.shop.authorizationservice.entities.UserEntity;

public interface UserEntityMapper<T> {
    UserEntity toUserEntity(T obj);
}
