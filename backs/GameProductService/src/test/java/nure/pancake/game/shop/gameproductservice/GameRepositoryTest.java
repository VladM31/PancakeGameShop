package nure.pancake.game.shop.gameproductservice;

import nure.pancake.game.shop.gameproductservice.entities.GameEntity;
import nure.pancake.game.shop.gameproductservice.entities.GenreEntity;
import nure.pancake.game.shop.gameproductservice.repositories.GameRepository;
import nure.pancake.game.shop.gameproductservice.search.criteria.GameSearchCriteria;
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
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;

    private GameEntity getGame() {
        return gameRepository.findAll(GameSearchCriteria.builder().build(), PageRequest.of(1, 1))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    public void testFindByGameId() {
        var gameId = getGame().getId();

        var game = gameRepository.findOne(
                        GameSearchCriteria.builder().gameId(gameId).build())
                .orElseThrow();

        Assertions.assertThat(game.getId())
                .isEqualTo(gameId);
    }

    @Test
    public void testFindByGenreId() {
        var genreId = getGame().getGenreEntities().stream().findFirst().orElseThrow().getId();

        var games = gameRepository.findAll(GameSearchCriteria.builder().genreId(genreId.longValue()).build());


        Assertions.assertThat(games)
                .map(GameEntity::getGenreEntities)
                .anyMatch(genre -> genre.stream().map(GenreEntity::getId).anyMatch(g -> g.equals(genreId)));
    }

    @Test
    public void testFindByGenreName() {
        var genreName = getGame().getGenreEntities().stream().findFirst().orElseThrow().getName();

        var games = gameRepository.findAll(
                GameSearchCriteria.builder().genreName(genreName).build());


        Assertions.assertThat(games)
                .map(GameEntity::getGenreEntities)
                .anyMatch(genre -> genre.stream().map(GenreEntity::getName).anyMatch(g -> g.contains(genreName)));
    }

    @Test
    public void testFindByPriceMore() {
        var price = getGame().getPrice();
        var games = gameRepository.findAll(
                GameSearchCriteria.builder().price(Range.ofFrom(price)).build());
        Assertions.assertThat(games)
                .allMatch(g -> g.getPrice() >= price);
    }

    @Test
    public void testFindByPriceLess() {
        var price = getGame().getPrice();
        var games = gameRepository.findAll(
                GameSearchCriteria.builder().price(Range.ofTo(price)).build());
        Assertions.assertThat(games)
                .allMatch(g -> g.getPrice() <= price);
    }

    @Test
    public void testFindByAgeRatingMore() {
        var ageRating = getGame().getAgeRating();
        var games = gameRepository.findAll(
                GameSearchCriteria.builder().ageRating(Range.ofFrom(ageRating.floatValue())).build());
        Assertions.assertThat(games)
                .allMatch(g -> g.getAgeRating() >= ageRating);
    }

    @Test
    public void testFindByAgeRatingLess() {
        var ageRating = getGame().getAgeRating();
        var games = gameRepository.findAll(
                GameSearchCriteria.builder().ageRating(Range.ofTo(ageRating.floatValue())).build());
        Assertions.assertThat(games)
                .allMatch(g -> g.getAgeRating() <= ageRating);
    }

    @After
    public void showSuccess() {
        System.out.println("Success");
    }
}
