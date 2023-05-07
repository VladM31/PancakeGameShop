package nure.pancake.game.shop.gameproductservice;

import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.repositories.LevelRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import nure.pancake.game.shop.gameproductservice.utils.Range;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LevelRepositoryTest {
    @Autowired
    private LevelRepository levelRepository;

    private LevelEntity getLevel() {
        return levelRepository.findAll(LevelSearchCriteria.builder().build(), PageRequest.of(1, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void testFindByLevelId() {
        var levelId = getLevel().getId();

        var level = levelRepository.findOne(
                        LevelSearchCriteria.builder().levelId(levelId).build())
                .orElseThrow();

        Assertions.assertThat(level.getId())
                .isEqualTo(levelId);
    }

    @Test
    public void testFindByGameId() {
        var gameId = getLevel().getGameId();
        var levels = levelRepository.findAll(
                LevelSearchCriteria.builder().gameId(gameId).build());

        Assertions.assertThat(levels)
                .allMatch(l -> l.getGameId().equals(gameId));
    }

    @Test
    public void testFindByName() {
        var name = getLevel().getName().substring(0, 2);
        var levels = levelRepository.findAll(
                LevelSearchCriteria.builder().name(name).build());
        Assertions.assertThat(levels)
                .allMatch(l -> l.getName().contains(name));
    }

    @Test
    public void testFindByPriceMore() {
        var price = getLevel().getPrice();
        var levels = levelRepository.findAll(
                LevelSearchCriteria.builder().price(Range.ofFrom(price)).build());
        Assertions.assertThat(levels)
                .allMatch(l -> l.getPrice() >= price);
    }

    @Test
    public void testFindByPriceLess() {
        var price = getLevel().getPrice();
        var levels = levelRepository.findAll(
                LevelSearchCriteria.builder().price(Range.ofTo(price)).build());
        Assertions.assertThat(levels)
                .allMatch(l -> l.getPrice() <= price);
    }

    @Test
    public void testFindByHiddenFalse() {
        var levels = levelRepository.findAll(
                LevelSearchCriteria.builder().hidden(false).build());
        Assertions.assertThat(levels)
                .noneMatch(LevelEntity::isHidden);
    }

    @After
    public void showSuccess() {
        System.out.println("Success");
    }
}
