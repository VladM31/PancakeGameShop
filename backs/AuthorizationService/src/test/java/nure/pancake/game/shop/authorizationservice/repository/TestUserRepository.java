package nure.pancake.game.shop.authorizationservice.repository;

import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.repositories.UserRepository;
import nure.pancake.game.shop.authorizationservice.search.criteria.UserSearchCriteria;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestUserRepository {
    private final static Consumer<UserSearchCriteria.UserSearchCriteriaBuilder> EMPTY_SEARCH = b -> {
    };
    @Autowired
    private UserRepository userRepository;

    private Stream<UserEntity> getUserStream(Consumer<UserSearchCriteria.UserSearchCriteriaBuilder> modifier) {
        return getUserStream(modifier, Pageable.ofSize(Integer.MAX_VALUE));
    }

    private Stream<UserEntity> getUserStream(Consumer<UserSearchCriteria.UserSearchCriteriaBuilder> modifier, Pageable pageable) {
        var builder = UserSearchCriteria.builder();
        modifier.accept(builder);
        return userRepository.findAll(builder.build(), pageable).stream();
    }

    private Optional<UserEntity> getAnyUser() {
        return getUserStream(EMPTY_SEARCH, Pageable.ofSize(4)).findAny();
    }

    @Test
    public void testFindByUserId() {
        var userId = getAnyUser().map(UserEntity::getId).orElseThrow();

        var user = getUserStream((b) -> b.userIds(List.of(userId))).findFirst().orElseThrow();

        Assertions.assertThat(user.getId())
                .isEqualTo(userId);
    }

    @Test
    public void testFindByPhoneNumberEquals() {
        var phoneNumber = getAnyUser().map(UserEntity::getPhoneNumber).orElseThrow();

        var user = getUserStream((b) -> b.phoneNumberEq(phoneNumber)).findFirst().orElseThrow();

        Assertions.assertThat(user.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    public void testFindByPhoneNumberContains() {
        var phoneNumber = getAnyUser().map(UserEntity::getPhoneNumber).orElseThrow().substring(0, 3);

        var users = getUserStream((b) -> b.phoneNumber(phoneNumber));

        Assertions.assertThat(users).allMatch(u -> u.getPhoneNumber().contains(phoneNumber));
    }

    @Test
    public void testFindByBirthDateAfterOrEquals() {
        var birthDay = getAnyUser().map(UserEntity::getBirthDate).orElseThrow();

        var user = getUserStream((b) -> b.birthDate(Range.ofFrom(birthDay)));

        Assertions.assertThat(user).map(UserEntity::getBirthDate).allMatch(b -> b.isAfter(birthDay) || b.isEqual(birthDay));
    }

    @Test
    public void testFindByBirthDateBeforeOrEquals() {
        var birthDay = getAnyUser().map(UserEntity::getBirthDate).orElseThrow();

        var user = getUserStream((b) -> b.birthDate(Range.ofTo(birthDay)));

        Assertions.assertThat(user).map(UserEntity::getBirthDate).allMatch(b -> b.isBefore(birthDay) || b.isEqual(birthDay));
    }

    @Test
    public void testFindByActive() {
        var user = getUserStream((b) -> b.active(true));

        Assertions.assertThat(user).allMatch(UserEntity::getActive);
    }

    @Test
    public void testFindByEmailIsNull() {
        var user = getUserStream((b) -> b.nullableEmail(true));

        Assertions.assertThat(user).map(UserEntity::getEmail).allMatch(Objects::isNull);
    }

}
