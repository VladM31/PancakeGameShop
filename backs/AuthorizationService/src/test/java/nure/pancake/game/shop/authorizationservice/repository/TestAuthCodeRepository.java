package nure.pancake.game.shop.authorizationservice.repository;

import nure.pancake.game.shop.authorizationservice.entities.AuthCodeEntity;
import nure.pancake.game.shop.authorizationservice.entities.UserEntity;
import nure.pancake.game.shop.authorizationservice.repositories.AuthCodeRepository;
import nure.pancake.game.shop.authorizationservice.search.criteria.AuthCodeSearchCriteria;
import nure.pancake.game.shop.authorizationservice.utils.Range;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestAuthCodeRepository {
    @Autowired
    private AuthCodeRepository repository;

    @Test
    public void testFindByValue() {
        var code = repository.findAll(new AuthCodeSearchCriteria()).stream().findFirst().orElseThrow();

        var foundCode = repository.findOne(AuthCodeSearchCriteria.builder().valueEq(code.getValue()).build()).orElseThrow();

        Assertions.assertThat(foundCode).isEqualTo(code);
    }

    @Test
    public void testFindByUserId() {
        var code = repository.findAll(new AuthCodeSearchCriteria()).stream().findFirst().orElseThrow();

        var codes = repository.findAll(AuthCodeSearchCriteria
                .builder()
                .userIds(List.of(code.getUserId()))
                .build());

        Assertions.assertThat(codes).allMatch(c -> c.getUserId().equals(code.getUserId()));
    }

    @Test
    public void testFindByDateOfCreationAfterOrEquals() {
        var dateOfCreation = repository
                .findAll(new AuthCodeSearchCriteria())
                .stream()
                .findAny()
                .orElseThrow()
                .getDateOfCreation();

        var user = repository.findAll(AuthCodeSearchCriteria
                .builder()
                .dateOfCreation(Range.ofFrom(dateOfCreation))
                .build());

        Assertions.assertThat(user)
                .map(AuthCodeEntity::getDateOfCreation)
                .allMatch(b -> b.isAfter(dateOfCreation) || b.isEqual(dateOfCreation));
    }

    @Test
    public void testFindByBirthDateBeforeOrEquals() {
        var dateOfCreation = repository
                .findAll(new AuthCodeSearchCriteria())
                .stream()
                .findAny()
                .orElseThrow()
                .getDateOfCreation();

        var user = repository.findAll(AuthCodeSearchCriteria
                .builder()
                .dateOfCreation(Range.ofTo(dateOfCreation))
                .build());

        Assertions.assertThat(user)
                .map(AuthCodeEntity::getDateOfCreation)
                .allMatch(b -> b.isBefore(dateOfCreation) || b.isEqual(dateOfCreation));
    }
}
