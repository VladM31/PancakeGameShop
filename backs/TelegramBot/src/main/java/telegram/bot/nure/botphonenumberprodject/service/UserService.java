package telegram.bot.nure.botphonenumberprodject.service;

import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserFilter;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findOne(UserUniqueFieldFilter filter);

    List<User> findAll(UserFilter filter);

    boolean update(User user);

    boolean save(User user);
}
