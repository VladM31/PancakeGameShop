package nure.pancake.game.shop.gameproductservice;

import nure.pancake.game.shop.gameproductservice.entities.LevelEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedGameEntity;
import nure.pancake.game.shop.gameproductservice.entities.PurchasedLevelEntity;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.repositories.PurchasedGameRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.LevelSearchCriteria;
import nure.pancake.game.shop.gameproductservice.search.criteria.PurchasedGameSearchCriteria;
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

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchasedGameRepositoryTest {
    @Autowired
    private PurchasedGameRepository purchasedGameRepository;

    private PurchasedGameEntity getPurchasedGame() {
        return purchasedGameRepository.findAll(PurchasedGameSearchCriteria.builder().build(), PageRequest.of(1, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void testFindByPurGameId() {
        var purGameId = getPurchasedGame().getId();

        var purGame = purchasedGameRepository.findOne(
                        PurchasedGameSearchCriteria.builder().purGameId(purGameId).build())
                .orElseThrow();

        Assertions.assertThat(purGame.getId())
                .isEqualTo(purGameId);
    }

    @Test
    public void testFindByPurLevelId() {
        var purLevelId = purchasedGameRepository.findAll(new PurchasedGameSearchCriteria())
                .stream().filter(g -> !g.getLevels().isEmpty()).findFirst().orElseThrow()
                .getLevels().stream().findFirst().orElseThrow().getId();

        var purLevels = purchasedGameRepository.findAll(
                        PurchasedGameSearchCriteria.builder().purLevelId(purLevelId).build())
                .stream().flatMap(g -> g.getLevels().stream()).toList();


        Assertions.assertThat(purLevels)
                .map(PurchasedLevelEntity::getId)
                .anyMatch(purLevelId::equals);
    }

    @Test
    public void testFindByGameId() {
        var gameId = getPurchasedGame().getGamesId();

        var games = purchasedGameRepository.findAll(
                PurchasedGameSearchCriteria.builder().gameId(gameId).build());

        Assertions.assertThat(games).allMatch(g -> g.getGamesId().equals(gameId));
    }

    @Test
    public void testFindByUserId() {
        var userId = getPurchasedGame().getUserId();

        var users = purchasedGameRepository.findAll(
                PurchasedGameSearchCriteria.builder().userId(userId).build());

        Assertions.assertThat(users).allMatch(g -> g.getUserId().equals(userId));
    }

    @Test
    public void testFindByPurGameBuyDateMore() {
        var purGameBuyDate = getPurchasedGame().getBuyDate();

        var date = purchasedGameRepository.findAll(
                PurchasedGameSearchCriteria.builder().purGameBuyDate(Range.ofFrom(purGameBuyDate)).build());

        Assertions.assertThat(date).allMatch(d -> d.getBuyDate().isAfter(purGameBuyDate) || d.getBuyDate().equals(purGameBuyDate));
    }

    @Test
    public void testFindByPurGameBuyDateLess() {
        var purGameBuyDate = getPurchasedGame().getBuyDate();

        var date = purchasedGameRepository.findAll(
                PurchasedGameSearchCriteria.builder().purGameBuyDate(Range.ofTo(purGameBuyDate)).build());

        Assertions.assertThat(date).allMatch(d -> d.getBuyDate().isBefore(purGameBuyDate) || d.getBuyDate().equals(purGameBuyDate));
    }

    @Test
    public void testFindByLevelId() {
        var levelId = purchasedGameRepository.findAll(new PurchasedGameSearchCriteria())
                .stream().filter(g -> !g.getLevels().isEmpty()).findFirst().orElseThrow()
                .getLevels().stream().findFirst().orElseThrow().getLevelsId();

        var levels = purchasedGameRepository.findAll(
                PurchasedGameSearchCriteria.builder().levelId(levelId).build());

        Assertions.assertThat(levels)
                .map(PurchasedGameEntity::getLevels)
                .anyMatch(level -> level.stream().map(PurchasedLevelEntity::getLevelsId).anyMatch(l -> l.equals(levelId)));
    }

    @After
    public void showSuccess() {
        System.out.println("Success");
    }
}
