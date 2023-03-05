package telegram.bot.nure.botphonenumberprodject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import telegram.bot.nure.botphonenumberprodject.entities.User;
import telegram.bot.nure.botphonenumberprodject.filter.UserFilter;
import telegram.bot.nure.botphonenumberprodject.filter.UserUniqueFieldFilter;
import telegram.bot.nure.botphonenumberprodject.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = {"users"}, key = "#filter.phoneNumber",condition = "#filter.phoneNumber!=null"),
                    @Cacheable(cacheNames = {"users"}, key = "#filter.chatId",condition = "#filter.chatId!=null")
            }
    )
    public Optional<User> findOne(UserUniqueFieldFilter filter) {
        return repository.findOne(filter);
    }

    @Override
    public List<User> findAll(UserFilter filter) {
        return repository.findAll(filter);
    }

    @Override
    @CacheEvict(value = "users", key = "#user.phoneNumber")
    public boolean update(User user) {
        return repository.update(user) != 0;
    }

    @Override

    @Caching(
            evict = {
                    @CacheEvict(value = "users", key = "#user.phoneNumber"),
                    @CacheEvict(value = "users", key = "#user.chatId")
            }
    )
    public boolean save(User user) {
        return repository.save(user) != 0;
    }
}
