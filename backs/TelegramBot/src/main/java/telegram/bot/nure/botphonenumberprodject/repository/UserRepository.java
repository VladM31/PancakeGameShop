package telegram.bot.nure.botphonenumberprodject.repository;

import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserFilter;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findOne(UserUniqueFieldFilter filter);

    List<User> findAll(UserFilter filter);

    int update(User id);

    int save(User id);
}
